package com.quxue.common.toolbox;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

/**
 * Created by houxg on 2015/6/25.
 */
public class SpannableTool {

    /**
     * 获取指定颜色，大小的SpannableString
     *
     * @param text  设定的文字
     * @param size  设定的文字大小，单位px
     * @param color 设定的文字颜色
     * @return 指定的SpannableString
     */
    public static SpannableString getSpannable(String text, int size, int color) {
        SpannableString spannableString = new SpannableString(text);
        setFontSize(spannableString, size, 0, spannableString.length());
        setFontColor(spannableString, color, 0, spannableString.length());
        return spannableString;
    }

    /**
     * 将指定字符串的末尾部分设为指定颜色
     *
     * @param front  前段文字
     * @param behind 后段需要指定颜色的文字
     * @param color  设定的文字颜色
     * @return 生成的SpannableString
     */
    public static SpannableString getBehindColor(String front, String behind, int color) {
        SpannableString text = new SpannableString(front + behind);
        setFontColor(text, color, front.length(), text.length());
        return text;
    }

    /**
     * 将指定字符串的起始部分设为指定颜色
     *
     * @param front  前段需要指定颜色的文字
     * @param behind 后段文字
     * @param color  设定的文字颜色
     * @return 生成的SpannableString
     */
    public static SpannableString getFrontColor(String front, String behind, int color) {
        SpannableString text = new SpannableString(front + behind);
        setFontColor(text, color, 0, front.length());
        return text;
    }

    /**
     * 将指定字符串的末尾部分设为指定颜色，大小
     *
     * @param front  前段文字
     * @param behind 后段需要指定颜色的文字
     * @param color  设定的文字颜色
     * @param size   设定的文字大小，单位px
     * @return 生成的SpannableString
     */
    public static SpannableString getBehind(String front, String behind, int color, int size) {
        SpannableString text = new SpannableString(front + behind);
        setFontSizeColor(text, size, color, front.length(), text.length());
        return text;
    }

    /**
     * 将指定字符串的起始部分设为指定颜色，大小
     *
     * @param front  前段需要指定颜色的文字
     * @param behind 后段文字
     * @param color  设定的文字颜色
     * @param size   设定的文字大小，单位px
     * @return 生成的SpannableString
     */
    public static SpannableString getFront(String front, String behind, int color, int size) {
        SpannableString text = new SpannableString(front + behind);
        setFontSizeColor(text, size, color, 0, front.length());
        return text;
    }

    /**
     * 设定文字的颜色，大小
     *
     * @param text  待设定的文字
     * @param size  设定的文字大小，单位px
     * @param color 设定的文字颜色
     * @param start 改变范围的起始index
     * @param end   改变范围的结束index(不包括该index)
     * @return 生成的SpannableString
     */
    public static Spannable setFontSizeColor(Spannable text, int size, int color, int start, int end) {
        text = setFontSize(text, size, start, end);
        text = setFontColor(text, color, start, end);
        return text;
    }

    /**
     * 设定文字的大小
     *
     * @param text  待设定的文字
     * @param size  设定的文字大小，单位px
     * @param start 改变范围的起始index
     * @param end   改变范围的结束index(不包括该index)
     * @return 生成的SpannableString
     */
    public static Spannable setFontSize(Spannable text, int size, int start, int end) {
        text.setSpan(new AbsoluteSizeSpan(size), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return text;
    }

    /**
     * 设定文字的颜色
     *
     * @param text  待设定的文字
     * @param color 设定的文字颜色
     * @param start 改变范围的起始index
     * @param end   改变范围的结束index(不包括该index)
     * @return 生成的SpannableString
     */
    public static Spannable setFontColor(Spannable text, int color, int start, int end) {
        text.setSpan(new ForegroundColorSpan(color), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return text;
    }

    /**
     * 设定文字的背景颜色
     *
     * @param text  待设定的文字
     * @param color 设定的文字背景颜色
     * @param start 改变范围的起始index
     * @param end   改变范围的结束index(不包括该index)
     * @return 生成的SpannableString
     */
    public static Spannable setBackGroundColor(Spannable text, int color, int start, int end) {
        text.setSpan(new BackgroundColorSpan(color), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return text;
    }

    /**
     * 设定文字的style
     *
     * @param text     待设定的文字
     * @param typeface 设定的style
     * @param start    改变范围的起始index
     * @param end      改变范围的结束index(不包括该index)
     * @return 生成的SpannableString
     */
    public static Spannable setStyle(Spannable text, int typeface, int start, int end) {
        text.setSpan(new StyleSpan(typeface), start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return text;
    }

}
