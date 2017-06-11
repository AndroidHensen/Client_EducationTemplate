package com.hensen.shixiongsdk.OkHttp.CallBack;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.hensen.shixiongsdk.OkHttp.Exception.OkHttpException;
import com.hensen.shixiongsdk.OkHttp.Listener.DisposeDataHandler;
import com.hensen.shixiongsdk.OkHttp.Listener.DisposeDataListener;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author 许英俊 2017/4/13
 */
public class CommonJsonCallBack implements Callback {

    //Exception message
    protected final String JSON_ERROR_MSG = "json_error";
    protected final String NETWORK_ERROR_MSG = "network_error";
    //Exception type
    protected final int NETWORK_ERROR = -1;
    protected final int JSON_ERROR = -2;
    protected final int OTHER_ERROR = -3;

    private Handler mHandler;
    private DisposeDataListener disposeDataListener;
    private Class<?> clazz;

    public CommonJsonCallBack(DisposeDataHandler disposeDataHandler) {
        this.disposeDataListener = disposeDataHandler.mListener;
        this.clazz = disposeDataHandler.mClass;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                disposeDataListener.onFailure(new OkHttpException(NETWORK_ERROR, e));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                handlerResponse(result);
            }
        });
    }

    /**
     * Handle the server response data
     *
     * @param responseObj
     */
    private void handlerResponse(Object responseObj) {
        if (responseObj == null || responseObj.toString().trim().equals("")) {
            disposeDataListener.onFailure(new OkHttpException(NETWORK_ERROR, NETWORK_ERROR_MSG));
            return;
        }
        try {
            if (clazz == null) {
                //返回数据
                disposeDataListener.onSuccess(responseObj);
            } else {
                //返回对象
                JSONObject result = new JSONObject(responseObj.toString());
                Object obj = new Gson().fromJson(result.toString(), clazz);
                if (obj != null) {
                    disposeDataListener.onSuccess(obj);
                } else {
                    disposeDataListener.onFailure(new OkHttpException(JSON_ERROR, JSON_ERROR_MSG));
                }
            }
        } catch (Exception e) {
            disposeDataListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
        }
    }
}
