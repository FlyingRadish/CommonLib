package com.quxue.common;

/**
 * Created by houxg on 2015/7/17.
 */
public abstract class CancelRunnable implements Runnable {

    private boolean isCancel = false;

    public void cancel() {
        isCancel = true;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public boolean isRunning() {
        return !isCancel;
    }
}
