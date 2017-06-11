package com.hensen.shixiongsdk.OkHttp.Listener;

import com.hensen.shixiongsdk.OkHttp.Exception.OkHttpException;

/**
 * @author 许英俊 2017/4/13
 */
public interface DisposeDataListener {

    void onSuccess(Object responseObj);

    void onFailure(OkHttpException exception);
}
