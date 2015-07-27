package com.quxue.common;

import com.android.volley.Response;

/**
 * Created by houxg on 2015/7/14.
 */
public abstract class CancelableListener<T> implements Response.Listener<T> {

    private boolean isCancel = false;

    public void cancel() {
        this.isCancel = true;
    }

    public boolean isCancel() {
        return isCancel;
    }
}
