package com.quxue.common.model;

/**
 * Created by houxg on 2015/6/23.
 */
public class NormalResponse {

    public static final int OK = 1;

    public int code;
    public String msg;

    public NormalResponse(int code) {
        this.code = code;
        msg = "";
    }

    public NormalResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
