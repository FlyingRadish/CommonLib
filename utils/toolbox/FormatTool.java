package org.houxg.pixiurss.utils.toolbox;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 格式化输出工具
 * <br>
 * author: houxg
 * <br>
 * create on 2015/7/18
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
     * 获取时间
     *
     * @param format      输出格式，例如"yyyy-MM-dd HH:mm:ss"
     * @param timeInMills 时间，单位为毫秒
     * @return 格式化输出
     */
    public static String getDate(String format, long timeInMills) {
        SimpleDateFormat formater = new SimpleDateFormat(format, Locale.CHINESE);
        return formater.format(new Date(timeInMills));
    }

    /**
     * @param joiner
     * @param timeInMills
     * @return HH:mm:ss
     */
    public static String getTime(String joiner, long timeInMills) {

        long mill = timeInMills % 1000;
        long sec = timeInMills / 1000;
        long min = sec / 60;
        sec = sec % 60;
        long hour = min / 24;
        min = min % 60;
        hour = hour % 24;

        return String.format("%02d" + joiner + "%02d" + joiner + "%02d", hour, min, sec);
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
            return String.valueOf(number);
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
        if (decCnt <= 0) {
            return String.valueOf(number);
        }

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

    /**
     * 日期转换为时间戳
     *
     * @param date_str
     * @param format
     * @return
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 整数秒转换为时分秒
     *
     * @param time
     * @return
     */
    public static String secToTime(int time) {
        if (time <= 0) {
            return "0秒";
        }
        if (time >= 60 * 60 * 99) {
            return "99小时59分59秒";
        }

        int sec = time % 60;
        int min = time / 60;
        int hour = min / 60;
        min = min % 60;

        String secStr;
        String minStr;
        String hourStr;

        boolean isMinZero = min <= 0;
        boolean isHourZero = hour <= 0;

        if (isHourZero) {
            hourStr = "";
        } else {
            hourStr = hour + "小时";
        }

        if (isHourZero && !isMinZero) {
            minStr = min + "分钟";
        } else if (!isHourZero && !isMinZero) {
            minStr = String.format("%02d秒", min);
        } else {
            minStr = "";
        }

        if (!isHourZero || !isMinZero) {
            secStr = String.format("%02d秒", sec);
        } else {
            secStr = sec + "秒";
        }

        return hourStr + minStr + secStr;
    }

}
