package app20181205.luis.com.myapplication.apkicon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;

import java.io.InputStream;

import app20181205.luis.com.myapplication.apps.InstalledAppInfo;

public class ApkIconModelLoader implements ModelLoader<InstalledAppInfo,InputStream> {
    private Context context;
    public ApkIconModelLoader(Context context){
        this.context = context;

    }

    @Nullable
    @Override
    public LoadData<InputStream> buildLoadData(@NonNull InstalledAppInfo installedAppInfo, int width, int height, @NonNull Options options) {
        return new LoadData<>(new ObjectKey(installedAppInfo.packageName),new ApkIconFetcher(context, installedAppInfo));
    }

    @Override
    public boolean handles(@NonNull InstalledAppInfo installedAppInfo) {
        return true;
    }
}

