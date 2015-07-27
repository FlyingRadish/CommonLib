package com.quxue.common.network;

import android.support.annotation.NonNull;

import com.quxue.common.model.NormalResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by houxg on 2015/7/15.
 */
public abstract class NormalFormRequest extends BaseFormRequest<NormalResponse> {


    public NormalFormRequest(String authority, String path, String mod) {
        super(authority, path, mod);
    }

    public NormalFormRequest(@NonNull URLHelper urlHelper) {
        super(urlHelper);
    }

    @Override
    protected NormalResponse parse(JSONObject rsp) throws ParseException, FailedException {
        try {
            int code = rsp.getInt("status");
            String msg = rsp.optString("msg", "");
            if (code == 1) {
                return new NormalResponse(code, msg);
            } else {
                throw new FailedException(code, msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new ParseException("parse error");
        }
    }
}
