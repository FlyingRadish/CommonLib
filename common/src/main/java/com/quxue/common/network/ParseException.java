package com.quxue.common.network;

/**
 * Created by houxg on 2015/6/23.
 */
public class ParseException extends com.android.volley.VolleyError {

    int code;

    public ParseException(int code, String exceptionMessage) {
        super(exceptionMessage);
        this.code = code;
    }

    public ParseException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
