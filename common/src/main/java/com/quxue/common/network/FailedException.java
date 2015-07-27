package com.quxue.common.network;

import com.android.volley.VolleyError;

/**
 * Created by houxg on 2015/6/23.
 */
public class FailedException extends VolleyError {

    int code;

    public FailedException(int code, String exceptionMessage) {
        super(exceptionMessage);
        this.code = code;
    }

    public FailedException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
