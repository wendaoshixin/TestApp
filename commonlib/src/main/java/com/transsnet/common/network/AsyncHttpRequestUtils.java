package com.transsnet.common.network;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.DownloadListener;
import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.interfaces.HttpCallBackInChildThreadListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.blankj.utilcode.util.LogUtils;

import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.security.cert.CertificateException;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;

public class AsyncHttpRequestUtils {
    private static final String TAG = "AsyncHttpRequestUtils";
    private static AsyncHttpRequestUtils ourInstance = new AsyncHttpRequestUtils();

//    public static AsyncHttpRequestUtils getInstanst() {
//        return ourInstance;
//    }
//
//    public AsyncHttpRequestUtils() {
//        AndroidNetworking.initialize(PalmplayApplication.getAppInstance().getApplicationContext(), getUnsafeOkHttpClient());
//        if (LogUtils.debug) {
//            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.HEADERS);
//        }
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inPurgeable = true;
//        AndroidNetworking.setBitmapDecodeOptions(options);
//        AndroidNetworking.setConnectionQualityChangeListener(new ConnectionQualityChangeListener() {
//            @Override
//            public void onChange(ConnectionQuality currentConnectionQuality, int currentBandwidth) {
//                LogUtils.d(TAG, "onChange: currentConnectionQuality : " + currentConnectionQuality + " currentBandwidth : " + currentBandwidth);
//            }
//        });
//    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            builder.protocols(Collections.singletonList(Protocol.HTTP_1_1))
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS);
            OkHttpClient okHttpClient = builder.build();

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * get请求
     * */
    public static void get(String url, RequestParams params, BaseParsedEventBusHttpListener responseHandler) {
        get(url, params, responseHandler, null);
    }

    /*
     * get请求 带tag用于取消
     * */
    public static void get(String url, RequestParams params, BaseParsedEventBusHttpListener responseHandler, Object tag) {
        realRequst(preGetRequst(url, params, tag), responseHandler);
    }

    /*
     * get请求
     * */
    public static void get(String url, RequestParams params, BaseStringEventBusHttpListener responseHandler) {
        get(url, params, responseHandler, null);
    }

    /*
     * get请求 带tag用于取消
     * */
    public static void get(String url, RequestParams params, BaseStringEventBusHttpListener responseHandler, Object tag) {
        realRequst(preGetRequst(url, params, tag), responseHandler);
    }


    /*
     * post请求 带tag用于取消
     * */
    public static void post(String url, RequestParams params, BaseParsedEventBusHttpListener responseHandler, Object tag) {
        realRequst(prePostRequst(url, params, tag), responseHandler);
    }

    /*
     * post请求, 参数放在请求体里面 带tag用于取消
     * */
    public static void postBody(String url, RequestParams params, BaseParsedEventBusHttpListener responseHandler, Object o) {
        ANRequest.PostRequestBuilder requestBuilde = AndroidNetworking.post(url).setTag(o);
        ConcurrentHashMap urlParamsMap = null;

        if (params != null) {
            urlParamsMap = params.getUrlParamsMap();
        }
        if (urlParamsMap != null) {
            requestBuilde.addBodyParameter(urlParamsMap);
        }
        requestBuilde.setPriority(Priority.LOW)
                .build()
                .getAsParsed(responseHandler.getTargetType(), (ParsedRequestListener) responseHandler);
    }

    private static ANRequest preGetRequst(String url, RequestParams params, Object tag) {
        ANRequest.GetRequestBuilder requestBuilde = AndroidNetworking.get(url).setTag(tag);
        ConcurrentHashMap urlParamsMap = null;

        if (params != null) {
            urlParamsMap = params.getUrlParamsMap();
        }
        if (urlParamsMap != null) {
//            Iterator<Map.Entry<String, String>> entries = urlParamsMap.entrySet().iterator();
//            while (entries.hasNext()) {
//                Map.Entry<String, String> entry = entries.next();
//                requestBuilde.addQueryParameter(entry.getKey(), entry.getValue());
//            }
            requestBuilde.addQueryParameter(urlParamsMap);
        }
        return requestBuilde.setPriority(Priority.LOW).build();
    }

    private static ANRequest prePostRequst(String url, RequestParams params, Object tag) {
        ANRequest.PostRequestBuilder requestBuilde = AndroidNetworking.post(url).setTag(tag);
        ConcurrentHashMap urlParamsMap = null;
        if (params != null) {
            urlParamsMap = params.getUrlParamsMap();
        }
        if (urlParamsMap != null) {
//            Iterator<Map.Entry<String, String>> entries = urlParamsMap.entrySet().iterator();
//            while (entries.hasNext()) {
//                Map.Entry<String, String> entry = entries.next();
//                requestBuilde.addQueryParameter(entry.getKey(), entry.getValue());
//            }
            requestBuilde.addQueryParameter(urlParamsMap);
        }
        return requestBuilde.setPriority(Priority.LOW).build();
    }

    private static void realRequst(ANRequest anRequest, Object responseHttpListener) {

//        anRequest.setAnalyticsListener(new AnalyticsListener() {
//            @Override
//            public void onReceived(long timeTakenInMillis, long bytesSent, long bytesReceived, boolean isFromCache) {
//
//                Log.d("AndroidNetworking", " timeTakenInMillis : " + timeTakenInMillis);
//                Log.d("AndroidNetworking", " bytesSent : " + bytesSent);
//                Log.d("AndroidNetworking", " bytesReceived : " + bytesReceived);
//                Log.d("AndroidNetworking", " isFromCache : " + isFromCache);
//
//            }
//        });
        if (responseHttpListener != null) {
            if (responseHttpListener instanceof BaseParsedEventBusHttpListener) {
                BaseParsedEventBusHttpListener httpListener = (BaseParsedEventBusHttpListener) responseHttpListener;
                Type targetType = httpListener.getTargetType();
                if (targetType != null) {
                    anRequest.getAsParsed(targetType, (ParsedRequestListener) responseHttpListener);
                } else {
                    throw new RuntimeException("At least set one return type");
                }
            } else if (responseHttpListener instanceof BaseStringEventBusHttpListener) {
                anRequest.getAsString((BaseStringEventBusHttpListener) responseHttpListener);
            }
            //可以续继续,添加其他返回类型
        }
    }

    public static void download(String downloadUrl, File downloadFile,
                                DownloadListener downloadListener, DownloadProgressListener downloadProgressListener,
                                Object tag) {


        AsyncHttpRequestUtils.download(downloadUrl, downloadFile, downloadListener, downloadProgressListener, null, tag);
    }

    public static void download(String downloadUrl, File downloadFile,
                                DownloadListener downloadListener, DownloadProgressListener downloadProgressListener,
                                HttpCallBackInChildThreadListener httpCallBackInChildThreadListener,
                                Object tag) {
        String filePath = downloadFile.getParentFile().getAbsolutePath();
        String fileName = downloadFile.getName();

        long starFrom = 0;
        if (downloadFile.exists() && downloadFile.canWrite()) {
            starFrom = downloadFile.length();
        }

        AsyncHttpRequestUtils.download(downloadUrl, filePath, fileName, starFrom, downloadListener, downloadProgressListener, httpCallBackInChildThreadListener, tag);
    }

    public static void download(String downloadUrl, String filePath, String fileName, long starFrom,
                                DownloadListener downloadListener, DownloadProgressListener downloadProgressListener,
                                HttpCallBackInChildThreadListener httpCallBackInChildThreadListener,
                                Object tag) {
        ANRequest.DownloadBuilder downloadBuilderANRequest = AndroidNetworking.download(downloadUrl, filePath, fileName);
        if (starFrom > 0) {
            downloadBuilderANRequest.addHeaders("Range", "bytes=" + starFrom + "-");
        }
        downloadBuilderANRequest.setTag(tag)
//					.addHeaders()
                .setPriority(Priority.LOW)
                .build()
                .setDownloadProgressListener(downloadProgressListener)
                .setHttpCallBackInChildThreadListener(httpCallBackInChildThreadListener)
                .startDownload(downloadListener);
    }

    /*取消带指定tag的请求*/
    public static void cancel(Object tag) {
        AndroidNetworking.cancel(tag);
    }

    /*取消所有请求*/
    public static void canelAll() {
        AndroidNetworking.cancelAll();
    }


    public static <M> void postObject(String url, M object, BaseParsedEventBusHttpListener responseHandler) {

        AndroidNetworking.post(url)
                .addApplicationJsonBody(object)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsParsed(responseHandler.getTargetType(), (ParsedRequestListener) responseHandler);
    }

    public static <M> void postObject(String url, M object, RequestParams params, BaseParsedEventBusHttpListener responseHandler, Object tag) {

        AndroidNetworking.post(url)
                .addApplicationJsonBody(object)
                .addQueryParameter(params.getUrlParamsMap())
                .setTag(tag)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsParsed(responseHandler.getTargetType(), (ParsedRequestListener) responseHandler);
    }

    public static void uploadFile(String url, String fileKey, File file, UploadProgressListener UploadProgressListener, BaseParsedEventBusHttpListener responseHandler, Object tag) {
        AndroidNetworking.upload(url)
//                .addMultipartFile("file", file)
//                .addMultipartParameter("key","value")
                .addMultipartFile(fileKey, file)
                .setTag(tag)
                .setPriority(Priority.HIGH)
                .build()
                .setUploadProgressListener(UploadProgressListener)
                .getAsParsed(responseHandler.getTargetType(), (ParsedRequestListener) responseHandler);

    }

}