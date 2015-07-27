package com.quxue.common.nuclear.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quxue.common.nuclear.presenter.NuclearPresenter;

/**
 * Created by houxg on 2015/7/10.
 */
public abstract class NuclearFragment<T extends NuclearPresenter> extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = generateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        connectWithPresenter(getPresenter());
        getPresenter().onCreate(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!isHidden()) {
            whileResume();
        }
    }

    protected void whileResume() {
        connectWithPresenter(getPresenter());
        getPresenter().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!isHidden()) {
            whilePause();
        }
    }

    protected void whilePause() {
        getPresenter().onPause();
        disconnectWithPresenter(getPresenter());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            whilePause();
        } else {
            whileResume();
        }
    }

    protected abstract T getPresenter();

    protected abstract void connectWithPresenter(T presenter);

    protected abstract void disconnectWithPresenter(T presenter);

    protected abstract View generateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState);
}
