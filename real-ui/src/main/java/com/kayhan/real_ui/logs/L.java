package com.kayhan.real_ui.logs;

import android.util.Log;

public class L {

    public final static String TAG = "Library_Kay";
    public static void i(String msg){
        Log.i(TAG,msg);
    }
    public static void e(String msg){
        Log.e(TAG,msg);
    }
    public static void e(Exception msg){
        Log.i(TAG,"Err: | "+msg);
    }
    public static void w(String msg){
        Log.w(TAG,msg);
    }
    public static void d(String msg){
        Log.d(TAG,msg);
    }

}
