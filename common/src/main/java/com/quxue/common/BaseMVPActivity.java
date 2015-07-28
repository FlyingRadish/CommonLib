package com.quxue.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.quxue.common.nuclear.presenter.NuclearPresenter;
import com.quxue.common.nuclear.view.NuclearActivity;
import com.quxue.common.toolbox.StatusBarTool;
import com.quxue.common.toolbox.ToastTool;

import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by houxg on 2015/6/19.
 */
public abstract class BaseMVPActivity<T extends NuclearPresenter> extends NuclearActivity<T> {
    TitleManager titleManager;

    @Override
    protected void initView() {
        setContentView(getLayoutId());
        titleManager = new TitleManager(this);
        titleManager.initTitle(getActionItems(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLeftBtnClicked(v);
            }
        });
        StatusBarTool.setStatusBarColor(this, getResources().getColor(R.color.status_bar));
        ButterKnife.bind(this);
    }

    public TitleManager getTitleManager() {
        return titleManager;
    }

    protected List<View> getActionItems() {
        return null;
    }

    @Override
    public void setTitle(CharSequence title) {
        titleManager.setTitle(title);
    }

    @Override
    public void setTitle(int titleId) {
        String title = getString(titleId);
        titleManager.setTitle(title);
    }

    public Bundle getStartParams() {
        Intent intent = getIntent();
        if (intent == null) {
            return null;
        } else {
            return intent.getExtras();
        }
    }

    float titleHeight;
    float lastY;
    View autoHideScrollView;
    View scrollContainer;

    public boolean isTitleShowComplete() {
        View titlePanel = titleManager.getTitlePanel();
        return titlePanel == null || titlePanel.getTranslationY() == 0;

    }

    /**
     * 设置自动隐藏标题，标题栏将随滚动向上移动隐藏，向下滚动时标题栏随滚动下移显示
     *
     * @param scrollView 滚动View
     * @param container  滚动View所在的容器View，或其本身
     */
    public void registerTitleAutoHide(View scrollView, View container) {
        if (!BuildConfig.ENABLE_TITLE_AUTO_HIDE) {
            return;
        }
        final View titlePanel = titleManager.getTitlePanel();
        if (titlePanel == null) {
            return;
        }

        unRegisterTitleAutoHide();  //注销掉此前的
        autoHideScrollView = scrollView;
        scrollContainer = container;

        titleHeight = getResources().getDimension(R.dimen.title_panel_height);
        autoHideScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float nowY = event.getRawY();

                if (action == MotionEvent.ACTION_MOVE) {
                    float dy = lastY == -1 ? 0 : nowY - lastY;  //获取移动距离
                    float offsetY = titlePanel.getTranslationY() + dy;
                    offsetY = offsetY > 0 ? 0 : offsetY;
                    offsetY = offsetY < -titleHeight ? -titleHeight : offsetY;
                    titlePanel.setTranslationY(offsetY);    //移动标题栏
                    scrollContainer.setTranslationY(offsetY);   //移动滚动View或其本身
                    lastY = nowY;   //记录本次的Y
                } else {
                    //ACTION_UP/ACTION_DOWN时，lastY会记录错误，因此设为-1,在进入ACTION_MOVE时，排除掉第一次事件
                    lastY = -1;
                }
                return false;
            }
        });
    }


    public void unRegisterTitleAutoHide() {
        if (!BuildConfig.ENABLE_TITLE_AUTO_HIDE) {
            return;
        }
        if (autoHideScrollView != null) {
            autoHideScrollView.setOnTouchListener(null);
            autoHideScrollView = null;
        }
        if (scrollContainer != null) {
            scrollContainer.setTranslationY(0);
            scrollContainer = null;
        }
        if (!isTitleShowComplete()) {
            View titlePanel = titleManager.getTitlePanel();
            if (titlePanel != null) {
                titlePanel.setTranslationY(0);
            }
        }
    }

    public void setRightButtonVisibility() {
        titleManager.setRightButtonVisible();
    }

    public void setRightButtonText(String text) {
        titleManager.setRightButtonText(text);
    }


    public void toast(String msg) {
        ToastTool.show(this, msg);
    }

    public void toast(int resId) {
        ToastTool.show(this, resId);
    }

    public Activity getActivity() {
        return this;
    }

    protected void onLeftBtnClicked(View view) {
        finish();
    }

    protected abstract int getLayoutId();
}
