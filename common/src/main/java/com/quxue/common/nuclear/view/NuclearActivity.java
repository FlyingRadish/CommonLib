package com.quxue.common.nuclear.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.quxue.common.nuclear.presenter.NuclearPresenter;


/**
 * Created by houxg on 2015/6/19.
 */
public abstract class NuclearActivity<T extends NuclearPresenter> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        connectWithPresenter(getPresenter());
        getPresenter().onCreate(savedInstanceState);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected void initView() {
    }

    protected abstract T getPresenter();

    @Override
    protected void onResume() {
        super.onResume();
        connectWithPresenter(getPresenter());
        getPresenter().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPresenter().onPause();
        disconnectWithPresenter(getPresenter());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().onDestroy();
    }

    protected abstract void connectWithPresenter(T presenter);

    protected abstract void disconnectWithPresenter(T presenter);
}
