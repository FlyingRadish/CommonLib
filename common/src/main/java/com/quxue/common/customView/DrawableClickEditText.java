package com.quxue.common.customView;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by houxg on 2015/7/28.
 */
public class DrawableClickEditText extends EditText {

    private static final int LEFT = 0;
    private static final int TOP = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 3;

    OnDrawableClickListener listener;

    public interface OnDrawableClickListener {

        int TOP = 1;
        int BOTTOM = 2;
        int LEFT = 3;
        int RIGHT = 4;

        void onClick(int target);
    }

    public DrawableClickEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Rect bound;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    int actionX, actionY;
                    actionX = (int) event.getX();
                    actionY = (int) event.getY();
                    Drawable[] drawables = getCompoundDrawables();

                    boolean hasLeft = drawables[0] != null;
                    boolean hasTop = drawables[1] != null;
                    boolean hasRight = drawables[2] != null;
                    boolean hasBottom = drawables[3] != null;

                    if (listener == null) {
                        return false;
                    }
                    int height = getMeasuredHeight();
                    int width = getMeasuredWidth();
                    int tempX, tempY;
                    if (hasLeft) {
                        bound = drawables[LEFT].getBounds();
                        tempX = actionX;
                        tempY = actionY - (height - bound.height()) / 2;
                        if (drawables[LEFT].getBounds().contains(tempX, tempY)) {
                            listener.onClick(LEFT);
                        }
                    }

                    if (hasTop) {
                        bound = drawables[TOP].getBounds();
                        tempX = actionX - (width - bound.width()) / 2;
                        tempY = actionY;
                        if (bound.contains(tempX, tempY)) {
                            listener.onClick(TOP);
                        }
                    }

                    if (hasRight) {
                        bound = drawables[RIGHT].getBounds();
                        tempX = actionX - (width - bound.width());
                        tempY = actionY - (height - bound.height()) / 2;
                        if (bound.contains(tempX, tempY)) {
                            listener.onClick(RIGHT);
                        }
                    }

                    if (hasBottom) {
                        bound = drawables[BOTTOM].getBounds();
                        tempX = actionX - (width - bound.width()) / 2;
                        tempY = actionY - (height - bound.height());
                        if (bound.contains(tempX, tempY)) {
                            listener.onClick(BOTTOM);
                        }
                    }
                }
                return false;
            }
        });
    }
}
