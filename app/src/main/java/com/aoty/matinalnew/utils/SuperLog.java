package com.aoty.matinalnew.utils;

import android.util.Log;

/**
 * Created by AotY on 2015/5/8.
 */
public class SuperLog {

    private static  final boolean isOpenLog = true;

    public static void e(String tagName , String msg)
    {
        if(isOpenLog)
        {
            Log.e(tagName , msg) ;
        }
    }

    public static void i(String tagName , String msg)
    {
        if(isOpenLog)
        {
            Log.i(tagName , msg) ;
        }
    }

    public static void d(String tagName , String msg)
    {
        if(isOpenLog)
        {
            Log.d(tagName , msg) ;
        }
    }

    public static void w(String tagName , String msg)
    {
        if(isOpenLog)
        {
            Log.w(tagName , msg) ;
        }
    }

    public static void v(String tagName , String msg)
    {
        if(isOpenLog)
        {
            Log.v(tagName , msg) ;
        }
    }


}
