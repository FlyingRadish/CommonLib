package org.houxg.pixiurss.utils.toolbox;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 通信功能相关
 * <br>
 * author: houxg
 * <br>
 * create on 2015/7/10
 **/
public class PhoneTool {

    /**
     * 向指定号码拨打电话
     *
     * @param ctx Context
     * @param phoneNumber 指定的号码
     */
    public static void call(Context ctx, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        ctx.startActivity(intent);
    }
}
