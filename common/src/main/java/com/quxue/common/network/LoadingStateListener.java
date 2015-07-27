package com.quxue.common.network;

import com.android.volley.Response;

/**
 * Created by houxg on 2015/7/14.
 */
public abstract class LoadingStateListener<T> implements Response.Listener<T> {
    boolean isLoading = false;

    public void start() {
        isLoading = true;
    }

    public void stop() {
        isLoading = false;
    }

    public boolean isLoading() {
        return isLoading;
    }
}
