package app20181205.luis.com.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.internal.ads.zzly;

import java.lang.reflect.Field;

import app20181205.luis.com.myapplication.admob.ReflectUtils;
import app20181205.luis.com.myapplication.admob.TmpReflectClass;

public class MainActivity extends AppCompatActivity {
    AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.e("test");
        View text_view = findViewById(R.id.text_view);
        text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, SecondActivity.class));
//                startActivity(new Intent(MainActivity.this, FullscreenActivity.class));


                ReflectUtils.removeAdViewInfoReflect(adView);

            }
        });

        adView = findViewById(R.id.adview);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.e("AdView", "onAdClosed");
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.e("AdView", "onAdFailedToLoad");
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.e("AdView", "onAdLeftApplication");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.e("AdView", "onAdOpened");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e("AdView", "onAdLoaded");
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                Log.e("AdView", "onAdClicked");
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                Log.e("AdView", "onAdImpression");
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
//        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
//        adView.setAdSize(AdSize.SMART_BANNER);
        String adId = adView.getAdUnitId();
        AdSize adSize = adView.getAdSize();

        Log.e("AdView", "广告：adId=" + adId + ", adSize=" + adSize);
        adView.loadAd(adRequest);
    }
}
