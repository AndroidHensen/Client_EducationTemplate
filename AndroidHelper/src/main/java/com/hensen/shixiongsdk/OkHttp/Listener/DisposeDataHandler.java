package com.hensen.shixiongsdk.OkHttp.Listener;

/**
 * @author 许英俊 2017/4/13
 */
public class DisposeDataHandler {

    public DisposeDataListener mListener;
    public Class<?> mClass;

    public DisposeDataHandler(DisposeDataListener disposeDataListener){
        this.mListener = disposeDataListener;
    }

    public DisposeDataHandler(DisposeDataListener disposeDataListener,Class<?> clazz){
        this.mListener = disposeDataListener;
        this.mClass = clazz;
    }
}
