package org.houxg.pixiurss.utils.logger;


/**
 * 网络方式输出
 * <br>
 * author: houxg
 * <br>
 * create on 2015/4/3
 */
public class NetLogger implements Log.LogNode {

    private final static int OUTPUT_PRIORITY = Log.WARN;

    @Override
    public void log(int priority, String tag, String content) {
        if (priority < OUTPUT_PRIORITY) {
            return;
        }
//        AVObject avObject = new AVObject("Bugs");
//        avObject.put("manufacturer", Build.MANUFACTURER);
//        avObject.put("product", Build.PRODUCT);
//        avObject.put("device", Build.DEVICE);
//        avObject.put("model", Build.MODEL);
//        avObject.put("display", Build.DISPLAY);
//        avObject.put("os", String.valueOf(Build.VERSION.SDK_INT));
//        avObject.put("pkgName", com.quxue.common.logger.Log.getApplicationId());
//        avObject.put("version", String.valueOf(com.quxue.common.logger.Log.getVersionCode()));
//        avObject.put("tag", tag);
//        avObject.put("detail", content);
//        avObject.saveInBackground();
    }
}
