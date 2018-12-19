package app20181205.luis.com.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

import app20181205.luis.com.myapplication.apkicon.ApkModelLoaderFactory;
import app20181205.luis.com.myapplication.apps.InstalledAppInfo;

@GlideModule
public class MyAppGlideModule extends AppGlideModule {
    public MyAppGlideModule() {
        super();
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
        registry.prepend(InstalledAppInfo.class,InputStream.class,new ApkModelLoaderFactory(context));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return super.isManifestParsingEnabled();
    }

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        super.applyOptions(context, builder);
    }
}
