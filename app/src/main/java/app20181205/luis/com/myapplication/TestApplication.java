package app20181205.luis.com.myapplication;

import android.app.Application;

public class TestApplication extends Application {

    private static TestApplication instance;

    public static TestApplication getAppInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }
}
