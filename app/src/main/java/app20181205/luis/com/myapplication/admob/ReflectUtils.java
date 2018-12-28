package app20181205.luis.com.myapplication.admob;

import android.util.Log;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.internal.ads.zzly;
import java.lang.reflect.Field;

public class ReflectUtils {

    public static void removeAdViewInfoReflect(AdView adView) {
        Log.e("AdView", "开始反射");

        try {


            Class<?> adViewClass = Class.forName("com.google.android.gms.ads.BaseAdView");
            Field zzutField = adViewClass.getDeclaredField("zzut");

//                    Field modifiersField = Field.class.getDeclaredField("modifiers"); //①
//                    Log.e("AdView", "modifiers**********");
//                    modifiersField.setAccessible(true);
//                    modifiersField.setInt(zzutField, zzutField.getModifiers() & ~Modifier.FINAL); //②

            zzutField.setAccessible(true);
            zzly zzut = (zzly) zzutField.get(adView);

            Class<?> zzlyClass = Class.forName("com.google.android.gms.internal.ads.zzly");
            Field zzyeField = zzlyClass.getDeclaredField("zzye");
            zzyeField.setAccessible(true);
            zzyeField.set(zzut, null);

            Log.e("AdView", "第一个反射的值=" + zzyeField.get(zzut));

            Field zzarhField = zzlyClass.getDeclaredField("zzarh");
            zzarhField.setAccessible(true);
            zzarhField.set(zzut, null);

            Log.e("AdView", "第二个反射的值=" + zzarhField.get(zzut));
            Log.e("AdView", "zzut反射赋值之后：adId=" + zzut.getAdUnitId() + ", adSize=" + zzut.getAdSize());


            zzutField.setAccessible(true);
            zzutField.set(adView, zzut);

            String adId = adView.getAdUnitId();
            AdSize adSize = adView.getAdSize();

            Log.e("AdView", "反射赋值之后：adId=" + adId + ", adSize=" + adSize);

        } catch (Exception e) {
            Log.e("AdView", "反射异常：" + e.toString());
        }


        Log.e("AdView", "完成反射");

    }
}
