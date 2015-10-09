package org.houxg.custmomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 分隔线
 * <br>
 * author: houxg
 * <br>
 * create on 2015/9/20
 */
public class Divider extends View {

    private final static int HORIZONTAL = 1;
    private final static int VERTICAL = 2;

    int dividerColor;
    Paint paint;

    float dividerWidth = 0;
    float paddingStart = 0;
    float paddingEnd = 0;
    int direction;
    boolean isCenter = false;
    boolean shouldDraw = true;

    Paint drawPaint;
    float drawablePaddingStart = 0;
    Bitmap extraTopDrawable;


    public Divider(Context context) {
        super(context);
        init(0xffffffff, 0);
    }

    public Divider(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Divider);
        dividerColor = typedArray.getColor(R.styleable.Divider_dividerColor, 0xffffffff);
        dividerWidth = typedArray.getDimension(R.styleable.Divider_dividerWidth, 0);
        paddingStart = typedArray.getDimension(R.styleable.Divider_startPadding, 0);
        paddingEnd = typedArray.getDimension(R.styleable.Divider_endPadding, 0);
        drawablePaddingStart = typedArray.getDimension(R.styleable.Divider_drawablePaddingStart, 0);
        direction = typedArray.getInt(R.styleable.Divider_dividerDirection, 1);
        isCenter = typedArray.getBoolean(R.styleable.Divider_center, false);
        shouldDraw = typedArray.getBoolean(R.styleable.Divider_draw, true);
        int topDrawAbleId = typedArray.getResourceId(R.styleable.Divider_extraDrawable, -1);
        typedArray.recycle();
        init(dividerColor, dividerWidth);
        if (topDrawAbleId != -1) {
            extraTopDrawable = BitmapFactory.decodeResource(getResources(), topDrawAbleId);
        }
    }

    public void init(int dividerColor, float dividerWidth) {
        this.dividerColor = dividerColor;
        if (paint == null) {
            paint = new Paint();
        }
        paint.setColor(dividerColor);
        paint.setStrokeWidth(dividerWidth);

        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
    }

    public void setPaddingStart(float paddingStart) {
        this.paddingStart = paddingStart;
        invalidate();
    }

    public void setPaddingEnd(float paddingEnd) {
        this.paddingEnd = paddingEnd;
        invalidate();
    }

    public void setDrawablePaddingStart(float paddingStart) {
        this.drawablePaddingStart = paddingStart;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int wid = 0;
        int hei = 0;

        switch (widthMode) {
            case MeasureSpec.UNSPECIFIED:
                wid = getSuggestedMinimumWidth();
                break;
            case MeasureSpec.AT_MOST:
                if (direction == VERTICAL) {
                    float bigWid = dividerWidth;
                    if (extraTopDrawable != null && extraTopDrawable.getWidth() > bigWid) {
                        bigWid = extraTopDrawable.getWidth();
                    }
                    wid = (int) (getPaddingLeft() + getPaddingRight() + bigWid);
                } else {
                    wid = getSuggestedMinimumWidth();
                }
                break;
            case MeasureSpec.EXACTLY:
                wid = widthSize;
                break;
        }

        switch (heightMode) {
            case MeasureSpec.UNSPECIFIED:
                hei = getSuggestedMinimumWidth();
                break;
            case MeasureSpec.AT_MOST:
                if (direction == VERTICAL) {
                    hei = getSuggestedMinimumHeight();
                } else {
                    float bigHei = dividerWidth;
                    if (extraTopDrawable != null && extraTopDrawable.getWidth() > bigHei) {
                        bigHei = extraTopDrawable.getHeight();
                    }
                    hei = (int) (getPaddingTop() + getPaddingBottom() + bigHei);
                }
                break;
            case MeasureSpec.EXACTLY:
                hei = heightSize;
                break;
        }

        setMeasuredDimension(wid, hei);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int wid = getWidth();
        int hei = getHeight();

        if (direction == HORIZONTAL) {
            float tempY = isCenter ? hei / 2 : getPaddingTop() + dividerWidth / 2;
            canvas.drawLine(getPaddingLeft() + paddingStart, tempY, wid - getPaddingRight() - paddingEnd, tempY, paint);
        } else {
            float tempX = isCenter ? wid / 2 : getPaddingLeft() + dividerWidth / 2;
            canvas.drawLine(tempX, getPaddingTop() + paddingStart, tempX, hei - getPaddingBottom() - paddingEnd, paint);
        }

        if (extraTopDrawable != null && shouldDraw) {
            float printX = getPaddingLeft();
            float printY = getPaddingTop();
            if (direction == HORIZONTAL) {
                printX += drawablePaddingStart;
            } else {
                printY += drawablePaddingStart;
            }
            canvas.drawBitmap(extraTopDrawable, printX, printY, drawPaint);
        }
    }
}
