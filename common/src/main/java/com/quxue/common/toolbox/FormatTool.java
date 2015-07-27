package com.quxue.common.toolbox;

/**
 * 格式化输出工具
 */
public class FormatTool {

    /**
     * 获取适于显示的磁盘空间大小
     *
     * @param size 空间字节数
     * @return 适于显示的空间大小字符串，根据大小会自动修改单位，保留2位小数
     */
    public static String getReadableDiskSpace(long size) {
        if (size < 1024) {
            return size + "B";
        } else if (size < 1024 * 1024) {
            return getWidthDecimal(size * 100 / 1024, 2, 2) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            return getWidthDecimal(size / 1024 * 100 / 1024, 2, 2) + "MB";
        } else {
            return getWidthDecimal(size / 1024 * 100 / 1024 / 1024, 2, 2) + "GB";
        }
    }

    /**
     * 获取固定宽度的整数字符串，不足则补0
     *
     * @param number 需要显示的整数
     * @param wid    设定显示的字符个数
     * @return 固定宽度的整数字符串，不足补0
     */
    //TODO:可能是错的
    public static String getWidthIntegerWithZeroPrefix(long number, int wid) {
        return String.format("%0" + wid + "d", number);
    }

    /**
     * 获取一个小数字符串，其小数部分宽度固定，宽度不足则在后补0
     *
     * @param number 需要显示的小数，去掉小数点以整数表示
     * @param decCnt 小数的位数
     * @param wid    小数部分固定的宽度
     * @return 满足条件的字符串
     */
    public static String getWidthDecimal(long number, int decCnt, int wid) {
        if (decCnt <= 0) {
            return number + "";
        }
        int tempPow = powTen(decCnt);
        long integer = number / tempPow;
        long decimal = number % tempPow;

        String decStr = String.format("%0" + wid + "d", decimal);
        return integer + "." + decStr;
    }

    /**
     * 获取一个小数字符串，尽量去掉小数部分末尾的0
     *
     * @param number 需要显示的小数，去掉小数点以整数表示
     * @param decCnt 小数的位数
     * @return 满足要求的字符串
     */
    public static String getReadableDecimal(long number, int decCnt) {

        int tempPow = powTen(decCnt);
        long integer = number / tempPow;
        long decimal = number % tempPow;

        if (decimal > 0) {
            String decStr = String.format("%0" + decCnt + "d", decimal);
            if (decStr.charAt(decStr.length() - 1) == '0') {
                int len = decStr.length();
                int index = len - 1;
                for (; index >= 0; index--) {
                    if (decStr.charAt(index) != '0') {
                        break;
                    }
                }
                decStr = decStr.substring(0, index + 1);
            }
            return integer + "." + decStr;
        } else {
            return integer + "";
        }
    }
    
    private static int powTen(int times) {
        if (times <= 0) {
            return 1;
        } else {
            int rslt = 1;
            while (times-- > 0) {
                rslt *= 10;
            }
            return rslt;
        }
    }
}
