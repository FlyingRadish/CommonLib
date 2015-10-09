package org.houxg.pixiurss.utils.toolbox;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 输入法工具
 * <br>
 * author: houxg
 * <br>
 * create on 2015/8/21
 */
public class IMETool {
    /**
     * 隐藏输入法键盘
     * @param v 任意View
     */
    public static void hideIME(View v) {
        InputMethodManager manager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null) {
            manager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
