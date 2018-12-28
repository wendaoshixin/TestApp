// TOP SECRET
package com.transsnet.common.network;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.google.gson.reflect.TypeToken;
import com.transsnet.common.EventMainThreadEntity;
import org.greenrobot.eventbus.EventBus;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseParsedEventBusHttpListener<T> implements ParsedRequestListener<T> {

    private EventMainThreadEntity mEventMainThreadEntity;

    private boolean isSuccess = false;

    public BaseParsedEventBusHttpListener(String action) {
        mEventMainThreadEntity = new EventMainThreadEntity();
        mEventMainThreadEntity.setAction(action);
    }

    @Override
    public void onResponse(T response) {
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
    public abstract void onSuccessPreProcess(T response);

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


    public Type getTargetType() {
        final Type tctualType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tctualType;
    }

    /*
    这两个方法必须实现一个优先使用Token,
    Token为空的时候再使用Class 需要用子类覆盖，目前验证不成功
    */
    public TypeToken getTypeToken() {
        return new TypeToken<T>() {
        };
    }

    public Class getTargetClass() {
        Type actualType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return actualType.getClass();
    }

    ;
}
