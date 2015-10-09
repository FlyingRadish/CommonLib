package org.houxg.pixiurss.utils.toolbox;

/**
 * Click有效性检查工具(防连点)
 * <br>
 * author: houxg
 * <br>
 * create on 2015/7/18
 */
public class ClickTool {
    private final static long MINIMUM_PERIOD = 300;
    private static long lastClick = 0;

    /**
     * 检查本次click是否无效
     *
     * @return true:无效(!)
     * false:有效
     */
    public synchronized static boolean isClickInvalid() {
        long now = System.currentTimeMillis();
        if (now - lastClick < MINIMUM_PERIOD) {
//            lastClick = now;
            return true;
        } else {
            lastClick = now;
            return false;
        }
    }
}
