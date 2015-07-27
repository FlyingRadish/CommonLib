package com.quxue.common.logger;

import android.util.Log;

import com.avos.avoscloud.AVObject;
import com.quxue.common.BuildConfig;


/**
 * Created by houxg on 2015/4/13.
 */
public class NetLogger implements LogNode {

    private final static int OUTPUT_PRIORITY = Log.ERROR;

    @Override
    public void log(int priority, String tag, String content) {
        if (priority < OUTPUT_PRIORITY) {
            return;
        }
        AVObject avObject = new AVObject("Bugs");
        avObject.put("pkgName", BuildConfig.APPLICATION_ID);
        avObject.put("version", BuildConfig.VERSION_CODE);
        avObject.put("detail", content);
        avObject.put("tag", tag);
        avObject.saveInBackground();
    }
}
