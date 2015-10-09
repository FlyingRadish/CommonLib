package org.houxg.pixiurss.utils.network;

import android.support.annotation.NonNull;

import com.android.volley.Response;

import org.houxg.pixiurss.utils.logger.Log;

/**
 * 通用Json请求构造器
 * 需要继承该类，增加一个by(params1, params2...)方法
 * 调用时，像这样：
 * new GetArticleAPI()
 *      .by(params1, params2)
 *      .subscribe(listener, errorListener)
 *      .send();
 */
public abstract class APIBuilder<T> {
    private static final String TAG = APIBuilder.class.getSimpleName();

    protected ParamsBuilder paramsBuilder;
    Response.Listener<T> listener;  //Volley.Listener
    Response.ErrorListener errorListener;
    Object tag;

    public APIBuilder(String authority, String path) {
        paramsBuilder = new ParamsBuilder(authority, path);
    }

    public APIBuilder(@NonNull ParamsBuilder builder) {
        this.paramsBuilder = builder;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public APIBuilder subscribe(Response.Listener<T> listener, Response.ErrorListener errorListener) {
        this.listener = listener;
        this.errorListener = errorListener;
        return this;
    }

    public NetworkRequest send() {
        String url = paramsBuilder.getUrl();
        Log.i(TAG, "url:" + url);
        NetworkRequest<T> request = new NetworkRequest<>(url, paramsBuilder.getPostParams(), listener, errorListener);
        request.setParser(getParser());
        request.setTag(tag);
        //将Request加入网络请求池
        //App.getVolleyQueue().add(request);
        return request;
    }

    protected abstract Parser<T> getParser();

}
