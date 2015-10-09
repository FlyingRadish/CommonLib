package org.houxg.pixiurss.utils.network;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.houxg.pixiurss.utils.logger.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 网络请求
 * Created by houxg on 2015/8/6.
 */
public class NetworkRequest<T> extends Request<JSONObject> {

    protected static final String CHARSET = "utf-8";
    private final static String TAG = NetworkRequest.class.getSimpleName();
    private final static String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=" + CHARSET;
    Map<String, String> postBody;

    private Response.Listener<T> listener;
    private Parser<T> parser;
    private boolean isDelivered = false;
    private String url;

    public NetworkRequest(String url, Map<String, String> params, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(params != null && params.size() > 0 ? Method.POST : Method.GET, url, errorListener);
        this.listener = listener;
        this.postBody = params;
        this.url = url;
        //设置重试策略
        RetryPolicy policy = new DefaultRetryPolicy(2500, 3, 0f);
        setRetryPolicy(policy);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        //将String转为Json对象，如果转换失败则抛出异常，转到Error。
        String jsonString = null;
        try {
            jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, CHARSET));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            Log.e(TAG, "url=" + url + ", rcv:" + jsonString);
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        //使用自定义的Parser对象解析Json对象，解析失败抛出异常，转到Error
        try {
            if (parser == null) {
                throw new ParseError();
            }
            isDelivered = true;
            T rslt = parser.parse(response);
            if (listener != null) {
                listener.onResponse(rslt);
            }
        } catch (ParseError | FailedError e) {
            e.printStackTrace();
            deliverError(e);
        }
    }

    @Override
    public void deliverError(VolleyError error) {
        isDelivered = true;
        super.deliverError(error);
    }

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
        //Form格式化请求串
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


    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }

    public boolean isDelivered() {
        return isDelivered;
    }
}
