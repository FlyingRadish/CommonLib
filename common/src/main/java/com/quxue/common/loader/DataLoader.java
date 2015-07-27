package com.quxue.common.loader;


import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houxg on 2014/12/22.
 */
public abstract class DataLoader<T> {


    protected List<T> data;
    protected int itemsInPage = 0;
    protected int nowPage = 1;
    protected boolean hasMore = true;
    protected boolean isLoading = false;
    protected OnLoadListener listener;
    protected OnLoadFailedListener failedListener;

    public DataLoader(int itemsInPage) {
        data = new ArrayList<>();
        this.itemsInPage = itemsInPage;
    }

    public abstract void stopLoad();

    public T getItem(int position) {
        if (data == null || data.size() == 0) {
            return null;
        }
        int len = data.size();
        if (position >= len || position < 0) {
            return null;
        }
        return data.get(position);
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setListener(OnLoadListener listener) {
        this.listener = listener;
    }

    public void setFailedListener(OnLoadFailedListener failedListener) {
        this.failedListener = failedListener;
    }

    public boolean hasMore() {
        return hasMore;
    }

    public boolean isLoading() {
        return isLoading;
    }

    protected void changeLoadingState(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public interface OnLoadListener {
        void onLoadSuccess(DataLoader loader, boolean hasMore);
    }

    public interface OnLoadFailedListener {
        void onFailed(DataLoader loader, VolleyError error);
    }
}
