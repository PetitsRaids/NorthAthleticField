package com.petits_raids.northathleticfield.utils;

import android.util.Log;

import com.petits_raids.northathleticfield.BuildConfig;

public class Logger {

    private static final String TAG = "Logger";

    private static boolean isDebug = BuildConfig.DEBUG;

    public static void d(String message) {
        if (!isDebug)
            return;
        Log.d(TAG, message);
    }
}
