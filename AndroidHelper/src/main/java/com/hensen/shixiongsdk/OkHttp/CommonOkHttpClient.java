package com.hensen.shixiongsdk.OkHttp;

import com.hensen.shixiongsdk.OkHttp.CallBack.CommonJsonCallBack;
import com.hensen.shixiongsdk.OkHttp.Https.HttpsUtils;
import com.hensen.shixiongsdk.OkHttp.Listener.DisposeDataHandler;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @author 许英俊 2017/4/13
 */
public class CommonOkHttpClient {

    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        //Support https
        builder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        //Support redirect
        builder.followRedirects(true);
        mOkHttpClient = builder.build();
    }

    /**
     * Send the request
     *
     * @param request
     * @param handler
     * @return
     */
    public static Call request(Request request, DisposeDataHandler handler) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallBack(handler));
        return call;
    }


}
