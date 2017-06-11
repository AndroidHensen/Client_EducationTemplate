package com.hensen.teachercontent.Api;

import com.hensen.shixiongsdk.OkHttp.CommonOkHttpClient;
import com.hensen.shixiongsdk.OkHttp.Listener.DisposeDataHandler;
import com.hensen.shixiongsdk.OkHttp.Listener.DisposeDataListener;
import com.hensen.shixiongsdk.OkHttp.Request.RequestFactory;
import com.hensen.shixiongsdk.OkHttp.Request.RequestParams;
import com.hensen.teachercontent.Bean.Data;
import com.hensen.teachercontent.Bean.DataList;

/**
 * @author 许英俊 2017/5/7
 */
public class RequestCenter {

//    //备用url
//    public static final String ROOT_URL = "http://218.15.154.154/teacher";
    //release url
    public static final String ROOT_URL = "http://218.15.154.154:8081";
    private static final String DATA_URL = ROOT_URL + "/admin.php";

    private static void getRequest(String url, RequestParams params,
                                   DisposeDataListener listener, Class clazz) {
        CommonOkHttpClient.request(RequestFactory.createGetRequest(url, params),
                new DisposeDataHandler(listener, clazz));
    }

    public static void requestBannerData(DisposeDataListener listener) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("c", "app");
        requestParams.put("a", "getBanner");
        getRequest(DATA_URL, requestParams, listener, DataList.class);
    }

    public static void requestNewsData(DisposeDataListener listener) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("c", "app");
        requestParams.put("a", "getNews");
        getRequest(DATA_URL, requestParams, listener, DataList.class);
    }

    public static void requestCourseData(DisposeDataListener listener) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("c", "app");
        requestParams.put("a", "getCourse");
        getRequest(DATA_URL, requestParams, listener, DataList.class);
    }

    public static void requestJobData(DisposeDataListener listener) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("c", "app");
        requestParams.put("a", "getJob");
        getRequest(DATA_URL, requestParams, listener, DataList.class);
    }

    public static void requestShareData(DisposeDataListener listener) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("c", "app");
        requestParams.put("a", "getShare");
        getRequest(DATA_URL, requestParams, listener, DataList.class);
    }

    public static void requestTeachsData(DisposeDataListener listener) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("c", "app");
        requestParams.put("a", "getTeach");
        getRequest(DATA_URL, requestParams, listener, DataList.class);
    }

    public static void requestThemeData(DisposeDataListener listener) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("c", "app");
        requestParams.put("a", "getTheme");
        getRequest(DATA_URL, requestParams, listener, DataList.class);
    }

    public static void requestVideoData(DisposeDataListener listener) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("c", "app");
        requestParams.put("a", "getVideo");
        getRequest(DATA_URL, requestParams, listener, DataList.class);
    }

    public static void requestAboutData(DisposeDataListener listener) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("c", "app");
        requestParams.put("a", "getAbout");
        getRequest(DATA_URL, requestParams, listener, DataList.class);
    }

}
