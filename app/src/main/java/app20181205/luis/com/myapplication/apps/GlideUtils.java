//// TOP SECRET
//package app20181205.luis.com.myapplication.apps;
//
//import android.app.Notification;
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.drawable.Drawable;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.RemoteViews;
//
//import com.afmobi.bg.BgUtils;
//import com.afmobi.palmplay.PalmplayApplication;
//import com.afmobi.palmplay.configs.v6_3.RankItemType;
//import com.afmobi.palmplay.setting.MsgNodeData;
//import com.bumptech.glide.DrawableTypeRequest;
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.bumptech.glide.load.resource.drawable.GlideDrawable;
//import com.bumptech.glide.request.RequestListener;
//import com.bumptech.glide.request.animation.GlideAnimation;
//import com.bumptech.glide.request.target.NotificationTarget;
//import com.bumptech.glide.request.target.SimpleTarget;
//import com.bumptech.glide.request.target.Target;
//
//import app20181205.luis.com.myapplication.GlideApp;
//
//public class GlideUtils {
//    public static void fitCenterInto(String url, RankItemType type, ImageView view) {
//        int size = 0; //DisplayUtil.dip2px(view.getContext(), 60);
//        Context context = view.getContext();
//        load(url, BgUtils.getRankItemDefaultDrawable(view, BgUtils.getRandomValue(), type, false), context).
//                fitCenter().
//                into(view);
//    }
//
//    public static Target into(String imgUrl, RankItemType type, int random, ImageView imageView) {
//        int size = 0;//DisplayUtil.dip2px(imageView.getContext(), 60);
//        return into(imgUrl, BgUtils.getRankItemDefaultDrawable(imageView, random, type, false), imageView);
//    }
//
//    public static void into(String imgUrl, int defResId, ImageView imageView) {
//        into(imgUrl, BgUtils.getBitmapDrawable(imageView, defResId), imageView);
//    }
//
//    public static void loadApkIcon(String path, int defResId, ImageView imageView) {
//        Glide.with(imageView.getContext())
//                .using(new ApplicationInfoLoader(imageView.getContext()), Drawable.class)
//                .from(String.class)
//                .as(Drawable.class)
//                .decoder(new ApplicationIconDecoder())
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
////                .skipMemoryCache(true)
//                .load(path)
////                .placeholder(BgUtils.getBitmapDrawable(imageView, defResId))
//                .placeholder(defResId)
//                .error(defResId)
//                .into(imageView);
//    }
//
//    private static Target into(String imgUrl, Drawable drawable, ImageView view) {
//        return load(imgUrl, drawable, view.getContext()).centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(view);
//    }
//
//    /*
//    skipMemoryCache 一般不设为true。 否则在刷新RecyclerView时会出现闪动。
//     */
//    public static DrawableTypeRequest<String> load(String url, Drawable drawable, Context context) {
//        DrawableTypeRequest<String> request = Glide.with(context).load(url);
//        request.dontAnimate();//.skipMemoryCache(true);
//
//        if (drawable != null) {
//            request.placeholder(drawable);
////                .placeholder(defResId)
//        }
//
//        return request;
//    }
//
//    public static void intoByColor(String imgUrl, ImageView imageView) {
//        intoByColor(imgUrl, imageView, -1);
//    }
//
//    public static void intoByColor(String imgUrl, ImageView imageView, int base) {
//        BgUtils.setDefaultBackgroundColor(imageView, base);
//        into(imgUrl, null, imageView);
//    }
//
//    public static void intoSize(String imgUrl, int width, int height, ImageView view) {
//        BgUtils.setDefaultBackgroundColor(view, BgUtils.getRandomValue());
//        load(imgUrl, null, view.getContext()).
//                centerCrop().
//                override(width, height).
//                into(view);
//    }
//
//    public static void intoSize(String imgUrl, int width, int height, int defResId, ImageView imageView, RequestListener listener) {
////        load(imgUrl, BgUtils.getBitmapDrawable(imageView, defResId), imageView.getContext()).
////                centerCrop().
////                override(width, height).
////                into(imageView);
//
//        GlideApp.with(imageView.getContext())
//                .load(imgUrl)
//                .placeholder(defResId)// 设置占位图
//                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy)// 缓存修改过的图片;
//                .override(width, height)
//                .listener(listener)
//                .into(imageView);
//    }
//
//    public static Target into(String url, final View view, int randomValue) {
//        BgUtils.setDefaultBackgroundColor(view, randomValue);
////        if (TextUtils.isEmpty(url)) {
////            ;
////        }
////        intoBitmapTarget(view.getContext(), url, new SimpleTarget<Bitmap>() {
////                    @Override
////                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
////                        if(resource!=null){
////                            view.setBackgroundDrawable(new BitmapDrawable(resource));
////                        }
////                    }
////                });
//
//        return intoTarget(view.getContext(), url, new SimpleTarget<GlideDrawable>() {
//            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
//                if (resource != null) {
//                    view.setBackgroundDrawable(resource);
//                }
//            }
//        });
//    }
//
//    //use NotificationTarget to load Notification image view.
//    public static void intoBitmapTarget(Context context, String url, SimpleTarget<Bitmap> target) {
//        Glide.with(context).load(url).asBitmap().into(target);
//    }
//
//    public static void intoNotification(Context context, String url, RemoteViews remoteViews, int viewId, int iconSize,
//                                        Notification notification, int notificationId) {
//        SimpleTarget<Bitmap> target = new NotificationTarget(context, remoteViews, viewId,
//                iconSize, iconSize, notification, notificationId);
//        Glide.with(context).load(url).asBitmap().into(target);
//    }
//
//    public static void intoNotification(Context context, String url, RemoteViews remoteViews, int viewId, int iconSize,
//                                        Notification notification, int notificationId, MsgNodeData msgNodeData) {
//        SimpleTarget<Bitmap> target = new CustomNotificationTarget(context, remoteViews, viewId,
//                iconSize, iconSize, notification, notificationId, msgNodeData);
//        Glide.with(context).load(url).into(target);
//    }
//
//    public static void intoNotification(Context context, String url, RemoteViews remoteViews, RemoteViews bigRemoteViews, int viewId, int iconSize,
//                                        Notification notification, int notificationId, MsgNodeData msgNodeData) {
//        SimpleTarget<Bitmap> target = new CustomNotificationTarget(context, remoteViews, bigRemoteViews, viewId,
//                iconSize, iconSize, notification, notificationId, msgNodeData);
//        Glide.with(context).load(url).asBitmap().into(target);
//    }
//
//    public static Target intoTarget(Context context, String url, SimpleTarget<GlideDrawable> target) {
//        return Glide.with(context).load(url).into(target);
//    }
//
//    public static void roundedCornersRes(String url, int defResId, ImageView imageView) {
//        Context mContext = PalmplayApplication.getAppInstance().getApplicationContext();// imageView.getContext()
//        load(url, BgUtils.getBitmapDrawable(imageView, defResId), mContext).
//                bitmapTransform(new RoundedCornersTransformation(mContext)).
//                into(imageView);
//    }
//
//    public static void into(String url, RankItemType type, int random, boolean isRound, ImageView imageView) {
//        if (isRound) {
//            Context mContext = PalmplayApplication.getAppInstance().getApplicationContext();// imageView.getContext()
//            load(url, BgUtils.getRankItemDefaultDrawable(imageView, random, type, false), mContext).
//                    bitmapTransform(new RoundedCornersTransformation(mContext)).
//                    into(imageView);
//        } else {
//            into(url, type, random, imageView);
//        }
//    }
//
//    public static void roundedCornersTransformation(String url, RankItemType type, int random, ImageView imageView) {
//        into(url, type, random, true, imageView);
//    }
//
//    public static void cropCircleTransformation(String url, RankItemType type, int random, boolean isOval, ImageView imageView) {
//        Context mContext = PalmplayApplication.getAppInstance().getApplicationContext();// imageView.getContext()
//
//        load(url, BgUtils.getRankItemDefaultDrawable(imageView, random, type, isOval), mContext).
//                bitmapTransform(new CropCircleTransformation(mContext)).
//                into(imageView);
//    }
//
//    public static void loadGif(int resId, ImageView gifView) {
//        Glide.with(gifView.getContext()).load(resId).asGif().into(gifView);
//    }
//}
