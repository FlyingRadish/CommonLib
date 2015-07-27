package com.quxue.common.network;

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by houxg on 2015/6/23.
 */
public class URLHelper {
    public final static String OAUTH = "Oauth";
    public final static String User = "User";
    private Uri.Builder builder;
    private Map<String, String> postParams;


    public URLHelper(String authority, String path) {
        builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority(authority).encodedPath(path);
        postParams = new HashMap<>();
    }

    public URLHelper addMod(String val) {
        builder.appendQueryParameter("mod", val);
        return this;
    }

    public URLHelper addAct(String val) {
        builder.appendQueryParameter("act", val);
        return this;
    }

    public URLHelper addGetParams(String key, String val) {
        builder.appendQueryParameter(key, val);
        return this;
    }

    public URLHelper addGetParams(String key, int val) {
        builder.appendQueryParameter(key, val + "");
        return this;
    }

    public URLHelper addGetParams(String key, double val) {
        builder.appendQueryParameter(key, val + "");
        return this;
    }

    public URLHelper addPostParams(String key, String val) {
        postParams.put(key, val);
        return this;
    }

    public URLHelper addPostParams(String key, int val) {
        postParams.put(key, val+"");
        return this;
    }

    public String getUrl() {
        return builder.build().toString();
    }

    public Map<String, String> getPostParams() {
        return postParams;
    }
}
