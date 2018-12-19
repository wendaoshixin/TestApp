package com.androidnetworking.interfaces;


/*
add by liuzhi 20180823 for download md5 check
优化网络回调， 耗时操作在子线程执行， 比如：
        1.文件下载MD5校验；
        2.请求保存缓存文件，数据耗时解释分析操作；
        3.数据库操作等等；
        */
public interface HttpCallBackInChildThreadListener<T> {
    void onCompletePreHandle(T response);
}
