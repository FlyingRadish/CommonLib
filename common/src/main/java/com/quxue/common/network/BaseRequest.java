package com.quxue.common.network;

import android.support.annotation.NonNull;

import com.android.volley.Response;
import com.quxue.common.App;
import com.quxue.common.logger.Log;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by houxg on 2015/7/15.
 */
public abstract class BaseRequest<T> {

    protected URLHelper urlHelper;
    Response.Listener<T> listener;
    Response.ErrorListener errorListener;

    public BaseRequest(String authority, String path, String mod) {
        urlHelper = new URLHelper(authority, path);
        urlHelper.addMod(mod);
        urlHelper.addAct(getAction());
    }

    public BaseRequest(@NonNull URLHelper urlHelper) {
        this.urlHelper = urlHelper;
        urlHelper.addAct(getAction());
    }

    protected URLHelper addGetToken(String token, String secret) {
        urlHelper.addGetParams("oauth_token", token)
                .addGetParams("oauth_token_secret", secret);
        return urlHelper;
    }

    protected URLHelper addPostToken(String token, String secret) {
        urlHelper.addGetParams("oauth_token", token)
                .addGetParams("oauth_token_secret", secret);
        return urlHelper;
    }

    protected abstract String getAction();

    public BaseRequest subscribe(Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this.listener = listener;
        this.errorListener = errorListener;
        return this;
    }


    public void send() {
        String url = urlHelper.getUrl();
        Log.i("common", "url:" + url);
        Map<String, String> postParams = urlHelper.getPostParams();
        VolleyRequest<T> request = new VolleyRequest<T>(url, postParams, listener, errorListener) {
            @Override
            public T parseToModel(JSONObject jObject) throws ParseException, FailedException {
                Log.i("common", "rcv:" + jObject.toString());
                return parse(jObject);
            }
        };
        App.getVolleyQueue().add(request);
    }

    protected abstract T parse(JSONObject rsp) throws ParseException, FailedException;
}
