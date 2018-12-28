// TOP SECRET
package app20181205.luis.com.myapplication.apps;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;

import java.text.Collator;
import java.util.Comparator;

public class InstalledAppInfo {
    public String appName = "";
    public String packageName = "";
    public String versionName = "";
    public int versionCode = 0;
    public long size = 0;
    public String filePath;
    public int installedState;
    public boolean isSelected = false;

    // add by HJG 2016-05-18
    public String sdCardRootAbsolutePath;
    public boolean isSystemApp = false;
    public long lastModified = 0;

    /**
     * 1- true; 0- flase
     */
    public int flag_InDownloaded = 0;

    public InstalledAppInfo() {
    }

    public InstalledAppInfo(PackageInfo packageInfo) {
        packageName = packageInfo.packageName;
        versionName = packageInfo.versionName;
        versionCode = packageInfo.versionCode;
        int flags = packageInfo.applicationInfo.flags;
//        isSystemApp = (flags & ApplicationInfo.FLAG_SYSTEM) > 0;
//        isSystemApp = !((flags & ApplicationInfo.FLAG_SYSTEM) <= 0);
        isSystemApp = ((flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0 ||
                (flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    @Override
    public String toString() {
        return "InstalledAppInfo [appName=" + appName
                + ", packageName=" + packageName
                + ", versionName=" + versionName
                + ", versionCode=" + versionCode
                + ", sdCardRootAbsolutePath=" + sdCardRootAbsolutePath
                + ", filePath=" + filePath
                + ", installedState=" + installedState
                + ", isSelected=" + isSelected
                + ", isSystemApp=" + isSystemApp
                + ", lastModified=" + lastModified
                + ", flag_InDownloaded=" + flag_InDownloaded + "]";
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getInstalledState() {
        return installedState;
    }

    public void setInstalledState(int installedState) {
        this.installedState = installedState;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSdCardRootAbsolutePath() {
        return sdCardRootAbsolutePath;
    }

    public void setSdCardRootAbsolutePath(String sdCardRootAbsolutePath) {
        this.sdCardRootAbsolutePath = sdCardRootAbsolutePath;
    }

    public boolean isSystemApp() {
        return isSystemApp;
    }

    public void setSystemApp(boolean systemApp) {
        isSystemApp = systemApp;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }

    public int getFlag_InDownloaded() {
        return flag_InDownloaded;
    }

    public void setFlag_InDownloaded(int flag_InDownloaded) {
        this.flag_InDownloaded = flag_InDownloaded;
    }

    public static class InstalledNameComparator implements Comparator<InstalledAppInfo> {
        //Collator collator= Collator.getInstance(java.util.Locale.CHINA);
        Collator collator = Collator.getInstance();

        @Override
        public int compare(InstalledAppInfo o1, InstalledAppInfo o2) {
            if (null == o1.appName) {
                o1.appName = "";
            }
            if (null == o2.appName) {
                o2.appName = "";
            }
            return collator.compare(o1.appName, o2.appName);
        }
    }
}