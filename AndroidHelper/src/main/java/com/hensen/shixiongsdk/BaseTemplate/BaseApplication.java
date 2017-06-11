package com.hensen.shixiongsdk.BaseTemplate;

import android.app.Application;

/**
 * =====作者=====
 * 许英俊
 * =====时间=====
 * 2016/5/main_bot_tab_home_off.
 */
public abstract class BaseApplication extends Application {

    public abstract void initConfigs();

    @Override
    public void onCreate() {
        super.onCreate();
        initConfigs();
    }

}
