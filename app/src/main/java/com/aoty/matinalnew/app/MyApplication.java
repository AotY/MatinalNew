package com.aoty.matinalnew.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by AotY on 2014/12/23.
 */
public class MyApplication extends Application {

    private static Context appContext;

    public static int SCREEN_WIDTH; // 屏幕 宽度
    public static int SCREEN_HEIGHT; // 屏幕 高度
    public static float SCREEN_DENSITY; // 屏幕 密度

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化全局 的Context
        appContext = getApplicationContext();
        init();
    }

    private void init() {
        SCREEN_WIDTH = getResources().getDisplayMetrics().widthPixels;
        SCREEN_HEIGHT = getResources().getDisplayMetrics().heightPixels;
        SCREEN_DENSITY = getResources().getDisplayMetrics().density;
    }

    public static Context getContext() {
        return appContext;
    }

    public static void setContext(Context appContext) {
        MyApplication.appContext = appContext;
    }
}
