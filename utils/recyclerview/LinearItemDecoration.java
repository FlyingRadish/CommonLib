package org.houxg.pixiurss.utils.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 适用于线性布局的ItemDecoration，可设定RecyclerView的Padding，以及item间的间距
 * <br>
 * author: houxg
 * <br>
 * create on 2015/9/13
 */
public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    int dividerWidth = 0;
    Rect paddingRect;

    boolean hasHeader = false;

    public LinearItemDecoration() {
    }

    /**
     * 设置内边距，单位px
     *
     * @param left   左内边距
     * @param top    顶内边距
     * @param right  右内边距
     * @param bottom 底内边距
     */
    public LinearItemDecoration(int left, int top, int right, int bottom) {
        paddingRect = new Rect(left, top, right, bottom);
    }

    /**
     * 设置item间距
     *
     * @param width 单位px
     * @return
     */
    public LinearItemDecoration setDividerWidth(int width) {
        dividerWidth = width;
        return this;
    }

    /**
     * 设置是否有Header
     * @param hasHeader
     */
    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int adapterPos = parent.getChildAdapterPosition(view);
        int itemCnt = parent.getAdapter().getItemCount();
        if (hasHeader) {
            adapterPos--;
            itemCnt--;
        }
        if (adapterPos == -1) {
            return;
        }

        outRect.left = paddingRect.left;
        outRect.bottom = 0;
        outRect.right = paddingRect.right;
        outRect.top = dividerWidth;

        if (adapterPos == 0) {
            outRect.top = paddingRect.top;
        }

        if (adapterPos == (itemCnt - 1)) {
            outRect.bottom = paddingRect.bottom;
        }
    }
}
