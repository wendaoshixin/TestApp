// TOP SECRET
package app20181205.luis.com.myapplication.apps;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;


import com.blankj.utilcode.util.LogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import app20181205.luis.com.myapplication.BuildConfig;
import app20181205.luis.com.myapplication.TestApplication;

public class InstalledAppManager {
    private static InstalledAppManager instance;
    private boolean mIsInitialized = false;
    private HashMap<String, InstalledAppInfo> mInstalledAppInfoMap = new HashMap<>();

    public static InstalledAppManager getInstance() {
        if (instance == null) {
            synchronized (InstalledAppManager.class) {
                if (instance == null)
                    instance = new InstalledAppManager();
            }
        }

        return instance;
    }

    private InstalledAppInfo getInstalledAppInfo(PackageManager pm, PackageInfo packageInfo, boolean isWithoutSelf) {
        InstalledAppInfo tmpInfo = null;
        if (pm != null && packageInfo != null) {
            if (!(isWithoutSelf && packageInfo.packageName.equals(BuildConfig.APPLICATION_ID))) {
                tmpInfo = new InstalledAppInfo(packageInfo);
                tmpInfo.appName = packageInfo.applicationInfo.loadLabel(pm).toString();
//                pm.getApplicationLabel(pm.getApplicationInfo(packageInfo.packageName, 0));

                try {
                    String apkFilePath = pm.getApplicationInfo(packageInfo.packageName, 0).sourceDir;
                    File apkFile = new File(apkFilePath);
                    tmpInfo.size = apkFile.length();
                    tmpInfo.filePath = apkFilePath;
                } catch (NameNotFoundException e) {
                    LogUtils.e(e);
                }
            }
        }
        return tmpInfo;
    }

    public List<InstalledAppInfo> getInstalledPackageList() {
        checkInitialize(true);
        List<InstalledAppInfo> installedApps = new ArrayList<>();
        for (InstalledAppInfo appInfo : getPackages()) {
//            if (appInfo != null &&
//                    !appInfo.isSystemApp &&
//                    BuildConfig.APPLICATION_ID.equals(appInfo.packageName)) {
//                installedApps.add(appInfo);
//            }
            installedApps.add(appInfo);
        }

        Collections.sort(installedApps, new InstalledAppInfo.InstalledNameComparator()); //按照升序
        //Collections.reverse(infoList);   //按照降序
        return installedApps;

//        PackageManager pm = AutoCheckApp.getAppInstance().getPackageManager();
//        List<PackageInfo> packages = pm.getInstalledPackages(0);
//        List<InstalledAppInfo> installedAppInfoList = new ArrayList<>();
//        for (PackageInfo packageInfo : packages) {
//            InstalledAppInfo tmpInfo = getInstalledAppInfo(pm, packageInfo, true);
//            if (tmpInfo != null)
//                installedAppInfoList.add(tmpInfo);
//        }
//        return installedAppInfoList;
    }

    public Integer getInstalledVersion(String packageName) {
        //内置版本需要加入应用自身
        if (BuildConfig.APPLICATION_ID.equals(packageName)) {
            return BuildConfig.VERSION_CODE;
        }

        InstalledAppInfo info = get(packageName);
        if (info == null) {
            return null;
        }

        return info.versionCode;
    }

    public InstalledAppInfo addPackage(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return null;
        }

        if (isInstalled(packageName)) {
            LogUtils.w("package " + packageName + " is already enqueue, just update it.");
        }

        InstalledAppInfo info = null;
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packInfo = pm.getPackageInfo(packageName, 0);
            info = getInstalledAppInfo(pm, packInfo, true);
            put(packageName, info);
        } catch (Exception e) {
            LogUtils.e(e);
        }

        return info;
    }

    public synchronized HashMap<String, InstalledAppInfo> initializePackageInfo() {
        if (mIsInitialized) {
            LogUtils.w("InstalledAppManage already initialized~~~");
            return mInstalledAppInfoMap;
        }
        LogUtils.w("InstalledAppManage initialized packages ...");


        mInstalledAppInfoMap.clear();
        PackageManager pm = TestApplication.getAppInstance().getPackageManager();
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo pkg : packages) {
            InstalledAppInfo info = getInstalledAppInfo(pm, pkg, false);
            if (info != null) {
                put(info.packageName, info);
            }
        }

        mIsInitialized = true;

        return mInstalledAppInfoMap;
    }

    synchronized void releaseAndClear() {
        if (mInstalledAppInfoMap != null) {
            mInstalledAppInfoMap.clear();
        }

        mIsInitialized = false;
    }

    public synchronized Collection<InstalledAppInfo> getPackages() {
        checkInitialize(true);
        return mInstalledAppInfoMap.values();
    }

    public synchronized boolean isNotInitialized() {
        return !mIsInitialized;
    }

    public synchronized boolean isInstalled(String packageName) {
        checkInitialize(true);
        return mInstalledAppInfoMap.containsKey(packageName);
    }

    private void checkInitialize(boolean doInitialized) {
        if (isNotInitialized()) {
            LogUtils.w("InstalledAppManager is not initialized");
            if (doInitialized) {
                initializePackageInfo();
                System.gc();
            }
        }
    }

    private boolean put(String packageName, InstalledAppInfo appInfo) {
        if (!TextUtils.isEmpty(packageName) && appInfo != null) {
            InstalledAppInfo info = mInstalledAppInfoMap.put(packageName, appInfo);
            if (info != null) {
                LogUtils.w("update package " + packageName + " information");
            }
            return true;
        }

        return false;
    }

    public synchronized InstalledAppInfo get(String packageName) {
        checkInitialize(true);
        return mInstalledAppInfoMap.get(packageName);
    }

    public synchronized InstalledAppInfo remove(String packageName) {
        checkInitialize(false);
        InstalledAppInfo info = mInstalledAppInfoMap.remove(packageName);
        if (info == null) {
            LogUtils.w("package " + packageName + " is not enqueue.");
        }
        return info;
    }


    public PackageInfo getPackageInfo(Context context, String name) {
        PackageInfo info = null; // 取得系统安装所有软件信息
        try {
            info = context.getPackageManager().
                    getPackageInfo(name, PackageManager.GET_UNINSTALLED_PACKAGES | PackageManager.GET_ACTIVITIES);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return info;
    }

    /**
     * 获取已安装Apk文件的源Apk文件
     * 如：/data/app/com.sina.weibo-1.apk
     */
    public String getMyselfSourceDir() {
        String packageName = BuildConfig.APPLICATION_ID;
        InstalledAppInfo appInfo = get(packageName);
        if (appInfo == null) {
            try {
                ApplicationInfo info = TestApplication.getAppInstance().
                        getPackageManager().
                        getApplicationInfo(packageName, 0);
                return info.sourceDir;
            } catch (NameNotFoundException e) {
                return null;
            }
        }

        return appInfo.filePath;
    }

    public static PackageInfo getInstalledSpecialApp(Context context, String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            //LogUtils.w(e);
        }
        return packageInfo;
    }

    public static boolean isSystemApp(PackageInfo packageInfo) {
        return (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0 ||
                (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
    }
}