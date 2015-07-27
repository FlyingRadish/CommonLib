package com.quxue.common;

import android.content.Context;

import com.android.volley.VolleyError;
import com.quxue.common.nuclear.presenter.NuclearPresenter;

/**
 * Created by houxg on 2015/7/16.
 */
public class BasePresenter extends NuclearPresenter {

    protected void onVolleyError(VolleyError error, Context ctx) {
        Standard.onVolleyError(error, ctx);
    }
}
