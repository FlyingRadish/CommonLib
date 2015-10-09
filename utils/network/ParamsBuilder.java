package org.houxg.pixiurss.utils.network;

import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

/**
 * HTTP GET/POST参数构造类
 */
public class ParamsBuilder {
    private Uri.Builder builder;
    private Map<String, String> postParams;
    private Map<String, String> getParams;


    public ParamsBuilder(String authority, String path) {
        builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority(authority).encodedPath(path);
        postParams = new HashMap<>();
        getParams = new HashMap<>();
    }

    public ParamsBuilder addGetParams(String key, String val) {
        getParams.put(key, val);
        return this;
    }

    public ParamsBuilder addGetParams(String key, long val) {
        getParams.put(key, String.valueOf(val));
        return this;
    }

    public ParamsBuilder addGetParams(String key, double val) {
        getParams.put(key, String.valueOf(val));
        return this;
    }

    public ParamsBuilder addPostParams(String key, String val) {
        postParams.put(key, val);
        return this;
    }

    public ParamsBuilder addPostParams(String key, int val) {
        postParams.put(key, String.valueOf(val));
        return this;
    }

    public ParamsBuilder addPostParams(String key, long val) {
        postParams.put(key, String.valueOf(val));
        return this;
    }

    public ParamsBuilder addPostParams(String key, double val) {
        postParams.put(key, String.valueOf(val));
        return this;
    }

    public String getUrl() {
        builder.clearQuery();
        for (Map.Entry<String, String> entry : getParams.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder.build().toString();
    }

    public Map<String, String> getPostParams() {
        return postParams;
    }
}
