package org.houxg.pixiurss.utils.toolbox;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.annotation.DimenRes;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ListView;

/**
 * UI相关工具
 * <br>
 * author: houxg
 * <br>
 * create on 2015/3/05
 */
public class UITool {

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static Drawable getDrawable(Context ctx, int resID) {
        Drawable drawable = ctx.getResources().getDrawable(resID);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        return drawable;
    }

    public static float getDimen(Context ctx, @DimenRes int resID) {
        return ctx.getResources().getDimension(resID);
    }

    /**
     * 计算ListView显示的尺寸
     * @param listView
     * @param showItemNum 显示多少项
     * @return ListView的宽/高，其高度值等于 n项高度+(第n+1项高度/2)，宽度等于最大宽度
     */
    private Point calListViewSize(ListView listView, int showItemNum) {
        int totalHeight = 0;
        int maxWidth = 0;

        int cnt_data = listView.getAdapter().getCount();
        if (cnt_data == 0) {
            return new Point(0, 0);
        }
        int max = showItemNum > cnt_data ? cnt_data : showItemNum;
        View listItem = null;
        for (int i = 0; i < max; i++) {
            listItem = listView.getAdapter().getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            maxWidth = listItem.getMeasuredWidth() > maxWidth ? listItem.getMeasuredWidth() : maxWidth;
        }
        // show half to let user see it.
        if (cnt_data > showItemNum) {
            assert listItem != null;
            totalHeight += listItem.getMeasuredHeight() / 2;
        }

        totalHeight = totalHeight + (listView.getDividerHeight() * (max - 1));
        return new Point(maxWidth, totalHeight);
    }


    public static boolean isPortrait(Context context) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return true;
        }
        return false;
    }

    public static boolean isLandscape(Context context) {
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return true;
        }
        return false;
    }

    public static Point getScreenResolution(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return new Point(dm.widthPixels, dm.heightPixels);
    }


    public static int getDiplayWidth(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getDiplayHeight(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int getDiplayDpi(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.densityDpi;
    }

}
