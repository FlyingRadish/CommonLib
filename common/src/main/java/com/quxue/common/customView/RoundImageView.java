package com.quxue.common.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.quxue.common.R;

/**
 * Created by houxg on 2015/6/25.
 */
public class RoundImageView extends ImageView {

    float gapWidth = 0;
    Path circle;
    private Paint paint;

    public RoundImageView(Context context) {
        super(context);
        initPaint();
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
        gapWidth = typedArray.getDimension(R.styleable.RoundImageView_whiteGapWidth, 0);
        typedArray.recycle();
        initPaint();
    }

    void initPaint() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        circle = new Path();
    }

    public float getGapWidth() {
        return gapWidth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int wid = getWidth();
        int hei = getHeight();
        canvas.drawCircle(wid / 2, hei / 2, wid / 2, paint);

        Drawable drawable = getDrawable();
        if (drawable == null) {
            return; // couldn't resolve the URI
        }
        int drawableWid = drawable.getIntrinsicWidth();
        int drawableHei = drawable.getIntrinsicHeight();
        if (drawableWid == 0 || drawableHei == 0) {
            return;     // nothing to draw (empty bounds)
        }
        circle.reset();
        circle.addCircle(wid / 2, hei / 2, wid / 2 - getGapWidth(), Path.Direction.CW);
        circle.close();
        canvas.clipPath(circle);
        drawable.draw(canvas);
    }
}
