package com.quxue.common.network.upload;

import com.android.volley.Response;
import com.quxue.common.logger.Log;
import com.quxue.common.network.FailedException;
import com.quxue.common.network.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by houxg on 2015/7/20.
 */
public abstract class UploadRequest<T> extends Uploader {

    Response.Listener<T> listener;

    public UploadRequest(String url, List<UploadParam> params, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(url, params, errorListener);
        this.listener = listener;
    }

    @Override
    void deliverResponse(String rsp) {
        try {
            JSONObject jsonObject = new JSONObject(rsp);
            T rslt = parseToModel(jsonObject);
            listener.onResponse(rslt);
        } catch (JSONException e) {
            deliveryError(new ParseException("parse error"));
        } catch (FailedException | ParseException e) {
            deliveryError(e);
        }
    }

    public abstract T parseToModel(JSONObject rsp) throws ParseException, FailedException;
}
