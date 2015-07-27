package com.quxue.common.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.quxue.common.R;


/**
 * Created by houxg on 2015/6/25.
 */
public class StrokeLinearLayout extends RelativeLayout {

    int pos;
    float wid;
    Paint paint;


    public StrokeLinearLayout(Context context) {
        super(context);
        paint = new Paint();
    }

    public StrokeLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StrokeLinearLayout);
        int color = typedArray.getColor(R.styleable.StrokeLinearLayout_strokeLineColor, 0xffffffff);
        wid = typedArray.getDimension(R.styleable.StrokeLinearLayout_strokeLineWidth, 0);
        pos = typedArray.getInt(R.styleable.StrokeLinearLayout_strokeLinePosition, 0);
        typedArray.recycle();

        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(wid);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        float tempY = getHeight() - wid;
        int wid = getWidth();

        switch (pos) {
            case 1:
                canvas.drawLine(0, 0, wid, 0, paint);
                break;
            case 2:
                canvas.drawLine(0, tempY, wid, tempY, paint);
                break;
            case 3:
                canvas.drawLine(0, 0, wid, 0, paint);
                canvas.drawLine(0, tempY, wid, tempY, paint);
                break;
        }

        super.onDraw(canvas);
    }
}
