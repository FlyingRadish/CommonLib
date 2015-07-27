package com.quxue.common.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.quxue.common.R;


/**
 * Created by houxg on 2015/6/25.
 */
public class RoundRectImageView extends ImageView {

    float rad = 0;
    Path circle;
    RectF rect;
    private Paint paint;

    public RoundRectImageView(Context context) {
        super(context);
        initPaint();
    }

    public RoundRectImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundRectImageView);
        rad = typedArray.getDimension(R.styleable.RoundRectImageView_rad, 0);
        typedArray.recycle();
        initPaint();
    }

    void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        circle = new Path();
        rect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return; // couldn't resolve the URI
        }
        int drawableWid = drawable.getIntrinsicWidth();
        int drawableHei = drawable.getIntrinsicHeight();
        if (drawableWid == 0 || drawableHei == 0) {
            return;     // nothing to draw (empty bounds)
        }

        int wid = getWidth();
        int hei = getHeight();
        rect.set(0, 0, wid, hei);
        circle.reset();
        circle.addRoundRect(rect, rad, rad, Path.Direction.CW);
        canvas.clipPath(circle);
        drawable.draw(canvas);
    }
}
