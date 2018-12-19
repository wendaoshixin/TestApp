package app20181205.luis.com.myapplication;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import app20181205.luis.com.myapplication.apps.InstalledAppInfo;
import app20181205.luis.com.myapplication.apps.InstalledAppManager;


public class SecondActivity extends AppCompatActivity {

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;

//    imgUrl1 = "http://cdn2.palmplaystore.com/static/858/07cb5b17c5fa4d59aefea7ac12105fbf.jpg",
//    imgUrl: "http://cdn2.palmplaystore.com/static/471/9a87a8ae713746b9982fb9412979bfb7.png",
//    imgUrl: "http://cdn2.palmplaystore.com/static/612/2f8d41bb828342e9b833b2a0ccfff30f.webp",
//    imgUrl: "http://cdn2.palmplaystore.com/static/118/351ba993b23241b3bd3050887c7136ef.jpg",
//    imgUrl: "http://cdn2.palmplaystore.com/static/812/2871ec8e0b2a4d77a7587206367ce50f.webp",


//    String imgUrl1 = "http://cdn2.palmplaystore.com/static/858/07cb5b17c5fa4d59aefea7ac12105fbf.jpg";
//    String imgUrl2 = "http://cdn2.palmplaystore.com/static/471/9a87a8ae713746b9982fb9412979bfb7.png";
//    String imgUrl3 = "http://cdn2.palmplaystore.com/static/612/2f8d41bb828342e9b833b2a0ccfff30f.webp";
//    String imgUrl4 = "http://cdn2.palmplaystore.com/static/118/351ba993b23241b3bd3050887c7136ef.jpg";
//    String imgUrl5 = "http://cdn2.palmplaystore.com/static/812/2871ec8e0b2a4d77a7587206367ce50f.webp";

    String imgUrl1 = "http://cdn2.palmplaystore.com/static/322/decd6560bce248948d9ea2bc06dfa138.jpg";
    String imgUrl2 = "http://cdn2.palmplaystore.com/static/927/6c15991f03154dc5b23dc48e90c166df.jpg";
    String imgUrl3 = "http://cdn2.palmplaystore.com/static/7/96a6f1921a8c458690fc6d7b9b4b0148.jpg";
    String imgUrl4 = "http://cdn2.palmplaystore.com/static/712/af277550567a46d48fbda0f20fb382cb.jpg";
    String imgUrl5 = "http://cdn2.palmplaystore.com/static/812/2871ec8e0b2a4d77a7587206367ce50f.webp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        LogUtils.e("test");
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);

////        GlideApp.with(this).load("http://goo.gl/gEgYUd").into(imageView);
//        Glide.with(this).load(imgUrl1).placeholder(R.mipmap.ic_launcher).into(imageView1);
//        Glide.with(this).load(imgUrl2).placeholder(R.mipmap.ic_launcher).into(imageView2);
//        Glide.with(this).load(imgUrl3).placeholder(R.mipmap.ic_launcher).into(imageView3);
//        Glide.with(this).load(imgUrl4).placeholder(R.mipmap.ic_launcher).into(imageView4);
//        Glide.with(this).load(imgUrl5).placeholder(R.mipmap.ic_launcher).into(imageView5);


        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .fallback(R.mipmap.ic_launcher);
//
//        Glide.with(this).load(imgUrl1).apply(requestOptions).into(imageView1);
//        Glide.with(this).load(imgUrl2).apply(requestOptions).into(imageView2);
//        Glide.with(this).load(imgUrl3).apply(requestOptions).into(imageView3);
//        Glide.with(this).load(imgUrl4).apply(requestOptions).into(imageView4);
//        Glide.with(this).load(imgUrl5).apply(requestOptions).into(imageView5);

        GlideApp.with(this).load(imgUrl1).placeholder(R.mipmap.default_image).into(imageView1);
        GlideApp.with(this).load(imgUrl2).placeholder(R.mipmap.default_image).into(imageView2);
        GlideApp.with(this).load(imgUrl3).placeholder(R.mipmap.default_image).into(imageView3);
        GlideApp.with(this).load(imgUrl4).placeholder(R.mipmap.default_image).into(imageView4);
        GlideApp.with(this).load(imgUrl5).placeholder(R.mipmap.default_image).into(imageView5);

        List<InstalledAppInfo> test = InstalledAppManager.getInstance().getInstalledPackageList();
        int index = new Random().nextInt(test.size());
        InstalledAppInfo installedAppInfo = test.get(index);/*new InstalledAppInfo("com.transsnet.store");*/
        GlideApp.with(this).load(installedAppInfo).placeholder(R.mipmap.ic_launcher).into(imageView5);
    }
}
