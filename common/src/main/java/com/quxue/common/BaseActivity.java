package com.quxue.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.quxue.common.toolbox.StatusBarTool;
import com.quxue.common.toolbox.ToastTool;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by houxg on 2015/6/19.
 */
public abstract class BaseActivity extends AppCompatActivity {

    TitleManager titleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void setRightButtonVisible(){
        titleManager.setRightButtonVisible();
    }

    public void setRightButtonText(String text){
        titleManager.setRightButtonText(text);
    }


    protected void onLeftBtnClicked(View view) {
        finish();
    }

    protected abstract int getLayoutId();

    public void toast(String msg) {
        ToastTool.show(this, msg);
    }

    public void toast(int resId) {
        ToastTool.show(this, resId);
    }
}
