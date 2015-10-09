package org.houxg.custmomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


/**
 * 实现上下描边的RelativeLayout
 * <br>
 * author: houxg
 * <br>
 * create on 2015/6/25
 */
public class StrokeRelativeLayout extends RelativeLayout {

    int pos;
    float wid;
    Paint paint;


    public StrokeRelativeLayout(Context context) {
        super(context);
        paint = new Paint();
    }

    public StrokeRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.StrokeRelativeLayout);
        int color = typedArray.getColor(R.styleable.StrokeRelativeLayout_strokeLineColor, 0xffffffff);
        wid = typedArray.getDimension(R.styleable.StrokeRelativeLayout_strokeLineWidth, 0);
        pos = typedArray.getInt(R.styleable.StrokeRelativeLayout_strokeLinePosition, 0);
        typedArray.recycle();

        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(wid);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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

    }
}
