package com.quxue.common;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.avos.avoscloud.AVOSCloud;
import com.quxue.common.logger.Log;

/**
 * Created by houxg on 2015/6/23.
 */
public class App extends Application implements Thread.UncaughtExceptionHandler {

    private final static String LEANCLOUDID = "yozgvkoqd83os9lizr4j22s2q87b8qfk2v1b8d6w38xdu3kw";
    private final static String LEANCLOUDKEY = "bi6hfh5dkl4b2po5ll3fwitwuzy37j06ytgv649u6kz6dzeu";
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
        AVOSCloud.initialize(this, LEANCLOUDID, LEANCLOUDKEY);
//        if (!BuildConfig.DEBUG) {
        Thread.setDefaultUncaughtExceptionHandler(this);
//        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e(BuildConfig.APPLICATION_ID, Log.getStackTraceString(ex));
        System.exit(0);
    }
}
