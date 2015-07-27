package com.quxue.common.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by houxg on 2015/7/21.
 */
public class SwipeToLoadMoreListView extends ListView {

    boolean is_divPage;
    private OnLoadMoreListener loadMoreListener;
    private OnScrollListener scrollListener;
    OnScrollListener onScrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (is_divPage && scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                if (loadMoreListener != null) {
                    loadMoreListener.onLoadMore();
                }
            } else if (!is_divPage && scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                if (loadMoreListener != null) {
                    loadMoreListener.onIdle();
                }
            }
            if (scrollListener != null) {
                scrollListener.onScrollStateChanged(view, scrollState);
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            is_divPage = (firstVisibleItem + visibleItemCount == totalItemCount);
            if (scrollListener != null) {
                scrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        }
    };

    public SwipeToLoadMoreListView(Context context) {
        super(context);

    }

    public SwipeToLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setOnScrollListener(onScrollListener);
    }

    @Override
    public void setOnScrollListener(OnScrollListener onScrollListener) {
        setScrollListener(onScrollListener);
    }

    public void setScrollListener(OnScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();

        void onIdle();
    }
}
