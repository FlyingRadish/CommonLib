package com.quxue.common;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.avos.avoscloud.AVOSCloud;
import com.quxue.common.logger.Log;

/**
 * Created by houxg on 2015/6/23.
 */
public abstract class App extends Application implements Thread.UncaughtExceptionHandler {

    private static RequestQueue queue;
    private static App ctx;

    public static RequestQueue getVolleyQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(ctx);
        }
        return queue;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        ctx = this;
        AVOSCloud.initialize(this, BuildConfig.LEANCLOUD_ID, BuildConfig.LEANCLOUD_KEY);
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e(BuildConfig.APPLICATION_ID, Log.getStackTraceString(ex));
        System.exit(0);
    }

    protected abstract String getReportBugTag();
}
