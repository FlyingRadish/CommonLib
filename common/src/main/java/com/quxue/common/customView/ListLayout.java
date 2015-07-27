package com.quxue.common.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

/**
 * Created by houxg on 2015/7/1.
 */
public class ListLayout extends LinearLayout {

    BaseAdapter adapter;

    public ListLayout(Context context) {
        super(context);
    }

    public ListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        if (adapter == null) {
            return;
        }
        int cnt = adapter.getCount();
        if (cnt <= 0) {
            return;
        }
        removeAllViews();
        for (int i = 0; i < cnt; i++) {
            View view = adapter.getView(i, null, this);
            addView(view, i);
        }
    }

    public void setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        init();
    }

}
