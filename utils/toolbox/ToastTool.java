package org.houxg.pixiurss.utils.toolbox;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Toast工具
 * <br>
 * author: houxg
 * <br>
 * create on 2015/6/27
 */
public class ToastTool {
    public static void show(Context context, String text) {
        Toast toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void show(Context context, @StringRes int resId) {
        Toast toast = Toast.makeText(context.getApplicationContext(), context.getText(resId), Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void show(Context context, CharSequence text) {
        Toast toast = Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
