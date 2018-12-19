package app20181205.luis.com.myapplication.apkicon;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;


import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.data.DataFetcher;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import app20181205.luis.com.myapplication.apps.InstalledAppInfo;

public class ApkIconFetcher implements DataFetcher<InputStream> {
    private InstalledAppInfo pkgName;
    private Context context;
    private final PackageManager packageManager;

    public ApkIconFetcher(Context context, InstalledAppInfo pkgName){
        this.context = context;
        this.pkgName = pkgName;
        packageManager = context.getPackageManager();
    }

    @Override
    public void loadData(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super InputStream> callback) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(pkgName.packageName, 0);
            Drawable d = packageManager.getApplicationIcon(applicationInfo); //xxx根据自己的情况获取drawable
            BitmapDrawable bd = (BitmapDrawable) d;
            Bitmap iconBitmap = bd.getBitmap();
            InputStream inputStream = bitmap2InputStream(iconBitmap);
            callback.onDataReady(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onLoadFailed(e);
        }
    }
    // 将Bitmap转换成InputStream
    private InputStream bitmap2InputStream(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }
    @Override
    public void cleanup() {

    }

    @Override
    public void cancel() {

    }

    @NonNull
    @Override
    public Class<InputStream> getDataClass() {
        return InputStream.class;
    }

    @NonNull
    @Override
    public DataSource getDataSource() {
        return DataSource.LOCAL;
    }
}

