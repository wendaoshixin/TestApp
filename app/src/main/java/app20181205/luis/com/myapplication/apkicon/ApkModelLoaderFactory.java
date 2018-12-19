package app20181205.luis.com.myapplication.apkicon;

import android.content.Context;
import android.support.annotation.NonNull;


import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

import app20181205.luis.com.myapplication.apps.InstalledAppInfo;

public class ApkModelLoaderFactory implements ModelLoaderFactory<InstalledAppInfo, InputStream> {
    private Context context;

    public ApkModelLoaderFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ModelLoader<InstalledAppInfo, InputStream> build(@NonNull MultiModelLoaderFactory multiFactory) {
        return new ApkIconModelLoader(context);
    }

    @Override
    public void teardown() {

    }
}

