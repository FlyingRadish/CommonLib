package com.quxue.common.network;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by houxg on 2015/6/23.
 */
public abstract class VolleyRequest<T> extends Request<JSONObject> {

    protected static final String CHARSET = "utf-8";
    private final static String TAG = VolleyRequest.class.getSimpleName();
    private final static String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=" + CHARSET;
    Map<String, String> postBody;
    private Response.Listener<T> listener;

    public VolleyRequest(String url, Map<String, String> params, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(params != null && params.size() > 0 ? Method.POST : Method.GET, url, errorListener);
        this.listener = listener;
        this.postBody = params;
        RetryPolicy policy = new DefaultRetryPolicy(2500, 3, 0f);
        setRetryPolicy(policy);
    }

    public VolleyRequest(String url, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.listener = listener;
        RetryPolicy policy = new DefaultRetryPolicy(2500, 3, 0f);
        setRetryPolicy(policy);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, CHARSET));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        try {
            T rslt = parseToModel(response);
            listener.onResponse(rslt);
        } catch (ParseException | FailedException e) {
            e.printStackTrace();
            getErrorListener().onErrorResponse(e);
        }
    }

    public Response.Listener<T> getListener() {
        return listener;
    }

    public abstract T parseToModel(JSONObject jObject) throws ParseException, FailedException;


    @Override
    public String getBodyContentType() {
        return CONTENT_TYPE;
    }

    @Override
    public byte[] getBody() {
        if (postBody == null || postBody.size() <= 0) {
            return null;
        }
        return encodeParameters(postBody, CHARSET);
    }

    private byte[] encodeParameters(Map<String, String> params, String paramsEncoding) {
        StringBuilder encodedParams = new StringBuilder();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                encodedParams.append(URLEncoder.encode(entry.getKey(), paramsEncoding));
                encodedParams.append('=');
                encodedParams.append(URLEncoder.encode(entry.getValue(), paramsEncoding));
                encodedParams.append('&');
            }

            return encodedParams.toString().getBytes(paramsEncoding);
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }
}
