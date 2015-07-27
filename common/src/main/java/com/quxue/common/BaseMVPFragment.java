package com.quxue.common;


import com.quxue.common.nuclear.presenter.NuclearPresenter;
import com.quxue.common.nuclear.view.NuclearFragment;
import com.quxue.common.toolbox.ToastTool;

/**
 * Created by houxg on 2015/7/14.
 */
public abstract class BaseMVPFragment<T extends NuclearPresenter> extends NuclearFragment<T> {

    public void toast(String msg) {
        ToastTool.show(getActivity(), msg);
    }

    public void toast(int resId) {
        ToastTool.show(getActivity(), resId);
    }
}
