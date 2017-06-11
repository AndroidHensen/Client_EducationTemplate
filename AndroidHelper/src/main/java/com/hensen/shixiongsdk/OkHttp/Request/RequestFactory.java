package com.hensen.shixiongsdk.OkHttp.Request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

/**
 * @author 许英俊 2017/4/13
 */
public class RequestFactory {

    /**
     * Returns A Post request
     */
    public static Request createPostRequest(String url, RequestParams params) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                formBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody body = formBuilder.build();
        return new Request.Builder().url(url).post(body).build();
    }

    /**
     * Returns A Get request
     */
    public static Request createGetRequest(String url, RequestParams params) {
        StringBuilder sb = new StringBuilder(url).append("?");
        if(params!= null){
            for (Map.Entry<String,String > entry:params.urlParams.entrySet()){
                sb.append(entry.getKey()).append("=")
                        .append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(sb.substring(0,sb.length()-1)).get().build();
    }
}
