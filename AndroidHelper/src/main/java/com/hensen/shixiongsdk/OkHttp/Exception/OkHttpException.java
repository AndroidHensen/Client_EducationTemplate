package com.hensen.shixiongsdk.OkHttp.Exception;

/**
 * @author 许英俊 2017/4/13
 */
public class OkHttpException extends Exception {

    private int ecode;
    private Object emsg;

    public OkHttpException(int ecode, Object emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public int getEcode() {
        return ecode;
    }

    public Object getEmsg() {
        return emsg;
    }

    @Override
    public String toString() {
        return "OkHttpException{" +
                "ecode=" + ecode +
                ", emsg=" + emsg +
                '}';
    }
}
