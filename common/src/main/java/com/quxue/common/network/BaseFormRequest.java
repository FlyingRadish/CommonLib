package com.quxue.common.network;

import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.quxue.common.logger.Log;
import com.quxue.common.network.upload.UploadParam;
import com.quxue.common.network.upload.UploadRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 表单上传基础类
 */
public abstract class BaseFormRequest<T> {

    protected URLHelper urlHelper;
    Response.Listener<T> listener;
    Response.ErrorListener errorListener;
    List<UploadParam> params;

    public BaseFormRequest(String authority, String path, String mod) {
        urlHelper = new URLHelper(authority, path);
        urlHelper.addMod(mod);
        urlHelper.addAct(getAction());
        params = new ArrayList<>();
    }


    public BaseFormRequest(@NonNull URLHelper urlHelper) {
        this.urlHelper = urlHelper;
        urlHelper.addAct(getAction());
        params = new ArrayList<>();
    }

    protected URLHelper addGetToken(String token, String secret) {
        urlHelper.addGetParams("oauth_token", token)
                .addGetParams("oauth_token_secret", secret);
        return urlHelper;
    }

    protected BaseFormRequest addUploadParam(UploadParam param) {
        params.add(param);
        return this;
    }

    protected abstract String getAction();

    public BaseFormRequest subscribe(Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this.listener = listener;
        this.errorListener = errorListener;
        return this;
    }


    public UploadRequest send() {
        String url = urlHelper.getUrl();
        Log.i("common", "url:" + url);
        UploadRequest<T> request = new UploadRequest<T>(url, params, listener, errorListener) {
            @Override
            public T parseToModel(JSONObject rsp) throws ParseException, FailedException {
                Log.i("common", "rcv:" + rsp);
                return parse(rsp);
            }
        };
        new Thread(request).start();
        return request;
    }

    protected abstract T parse(JSONObject rsp) throws ParseException, FailedException;
}
