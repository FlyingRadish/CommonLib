package org.houxg.pixiurss.utils.toolbox;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络状态获取
 * <br>
 * author: houxg
 * <br>
 * create on 2015/7/28
 */
public class NetworkStateTool {

    public static boolean isWifiConnected(Context ctx) {
        ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info != null && info.isConnected();
    }

    public static boolean isMobileConnected(Context ctx) {
        ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return info != null && info.isConnected();
    }

    public static boolean isNetworkConnected(Context ctx){
        ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isConnected();
    }
}
