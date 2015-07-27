package com.quxue.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.quxue.common.logger.Log;
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
    boolean canMove;
    boolean lastState;
    float lastX;

    //TODO:un-finish
    public void enableTitleAutoHide(ListView listView) {
        final View titlePanel = titleManager.getTitlePanel();
        if (titlePanel == null) {
            return;
        }

        titleHeight = getResources().getDimension(R.dimen.title_panel_height);

        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float nowX = event.getRawX();
                if (action == MotionEvent.ACTION_DOWN) {
                    lastX = nowX;
                    Log.i("houxg", "change");
                } else if (action == MotionEvent.ACTION_MOVE) {
                    float dx = nowX - lastX;
                    float titleY = titlePanel.getY();
                    float finalY = titleY - dx / 2;
                    finalY = finalY > 0 ? 0 : finalY;
                    finalY = finalY < -titleHeight ? -titleHeight : finalY;
                    titlePanel.setY(finalY);
                    Log.i("houxg", "move:" + dx + ", to:" + finalY);
                }
                return false;
            }
        });
    }


    public void setRightButtonVisibility() {
        titleManager.setRightButtonVisible();
    }

	public void setRightButtonText(String text){
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
