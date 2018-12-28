// TOP SECRET
package com.transsnet.common.network;


import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.transsnet.common.EventMainThreadEntity;

import org.greenrobot.eventbus.EventBus;


public abstract class BaseStringEventBusHttpListener implements StringRequestListener {

    private EventMainThreadEntity mEventMainThreadEntity;

    private boolean isSuccess = false;

    public BaseStringEventBusHttpListener(String action) {
        mEventMainThreadEntity = new EventMainThreadEntity();
        mEventMainThreadEntity.setAction(action);
    }

    @Override
    public void onResponse(String response) {
        isSuccess = true;

        onSuccessPreProcess(response);

        callback(isSuccess);
    }

    @Override
    public void onError(ANError anError) {
        isSuccess = false;
//
        onFailurePreProcess(anError);

        callback(isSuccess);
    }

    /**
     * abstract method .
     *
     * @param response
     */
    public abstract void onSuccessPreProcess(String response);

    /**
     * abstract method .
     *
     * @param anError
     */
    public abstract void onFailurePreProcess(ANError anError);

    /**
     * 如果使用该类的直接或间接子类返回可回调标记, 则使用EventBus回调, 参数封装在HashMap<String, Object>中。
     * 方法enableCallback()返回回调标记;
     *
     * @param isSuccess
     */
    final protected void callback(boolean isSuccess) {
        if (enableCallback()) {
            mEventMainThreadEntity.isSuccess = isSuccess;
            putExtraData(mEventMainThreadEntity);
            postEventBus(mEventMainThreadEntity);
        }
    }

    final protected void callback() {
        if (enableCallback()) {
            mEventMainThreadEntity.isSuccess = isSuccess;
            putExtraData(mEventMainThreadEntity);
            postEventBus(mEventMainThreadEntity);
        }
    }


    public void postEventBus(EventMainThreadEntity mEventMainThreadEntity) {
        EventBus.getDefault().post(mEventMainThreadEntity);
    }

    public abstract void putExtraData(EventMainThreadEntity mEventMainThreadEntity);

    /**
     * Note: important .
     * <p>
     * abstract method .
     *
     * @return true : excute callback() method , else false .
     */
    public abstract boolean enableCallback();

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
