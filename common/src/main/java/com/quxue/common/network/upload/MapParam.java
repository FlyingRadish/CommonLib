package com.quxue.common.network.upload;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by houxg on 2015/7/20.
 */
public class MapParam implements UploadParam {

    private final static String paramsEncoding = "utf-8";
    Map<String, String> params;

    public MapParam() {
        params = new HashMap<>();
    }

    public MapParam add(String key, String val) {
        params.put(key, val);
        return this;
    }

    @Override
    public String getHead() {
        return "Content-Disposition: form-data; name=\"params\"";
    }

    @Override
    public InputStream getInputStream() {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }
            String rslt = encodedParams.toString();

            return new ByteArrayInputStream(rslt.getBytes(paramsEncoding));
        } catch (UnsupportedEncodingException uee) {
            return null;
        }
    }

    @Override
    public int getType() {
        return UploadParam.TYPE_STREAM;
    }
}
