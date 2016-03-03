package com.guider.beauty.utils;

import android.util.Log;


public class L {

    private static final String TAG = "zjw";
    private static final boolean isDebug =true;

    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(TAG, "[" + tag + "]" + msg);
    }

    public static void i( String msg) {
        if (isDebug)
            Log.i(TAG,  msg);
    }
}
