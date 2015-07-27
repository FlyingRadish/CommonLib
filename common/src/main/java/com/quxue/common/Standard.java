package com.quxue.common;

import android.content.Context;

import com.android.volley.VolleyError;
import com.quxue.common.network.FailedException;
import com.quxue.common.network.ParseException;
import com.quxue.common.toolbox.ToastTool;

/**
 * Created by houxg on 2015/7/21.
 */
public class Standard {
    public static void onVolleyError(VolleyError error, Context ctx) {
        if (error instanceof FailedException) {
            ToastTool.show(ctx, error.getMessage());
        } else if (error instanceof ParseException) {
            ToastTool.show(ctx, "解析错误");
        } else {
            ToastTool.show(ctx, "网络错误");
        }
    }
}
