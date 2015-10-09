package org.houxg.pixiurss.utils.network;

import com.android.volley.VolleyError;

/**
 * API返回错误
 * Created by houxg on 2015/8/6.
 */
public class FailedError extends VolleyError {
    private int code;

    public FailedError() {
    }

    public FailedError(String exceptionMessage) {
        super(exceptionMessage);
    }

    public FailedError(int code, String exceptionMessage) {
        super(exceptionMessage);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
