package com.quxue.common.customView;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.quxue.common.R;

/**
 * Created by houxg on 2015/7/8.
 */
public class LoadingCircle extends PopupWindow {

    View view;
    ImageView circle;
    ObjectAnimator animator;

    public LoadingCircle(Context ctx, ViewGroup parent) {
        super();
        view = LayoutInflater.from(ctx).inflate(R.layout.pop_loading, parent, false);
        circle = (ImageView) view.findViewById(R.id.img_loading);
        setContentView(view);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        Drawable backGround = ctx.getResources().getDrawable(R.drawable.bg_loading);
        setBackgroundDrawable(backGround);
        setTouchable(false);
        setOutsideTouchable(false);
        update();
        animator = ObjectAnimator.ofFloat(circle, ImageView.ROTATION, 0, 360);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(800);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
    }


    public void startLoading(View anchor) {
        if (!isShowing()) {
            animator.start();
            showAtLocation(anchor, Gravity.CENTER, 0, 0);
        }
    }

    public void stopLoading() {
        if (isShowing()) {
            animator.end();
            dismiss();
        }
    }

    public void destroy() {
        dismiss();
    }
}
