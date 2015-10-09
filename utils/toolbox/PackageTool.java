package org.houxg.pixiurss.utils.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * APK包相关工具
 * <br>
 * author: houxg
 * <br>
 * create on 2015/7/22
 **/
public class PackageTool {

    /**
     * 获取当前版本名称
     *
     * @param ctx Context
     * @return 当前版本名称
     */
    public static String getVersionName(Context ctx) {
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前版本编号
     *
     * @param ctx Context
     * @return 当前版本名称
     */
    public static int getVersionCode(Context ctx) {
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取指定包名的应用信息
     *
     * @param ctx         Context
     * @param packageName 指定的包名
     * @return 指定的应用信息， null表示未找到
     */
    public static PackageInfo getPackageInfo(Context ctx, String packageName) {
        try {
            return ctx.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
}
