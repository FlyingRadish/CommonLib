package com.quxue.common.toolbox;

/**
 * Created by houxg on 2015/7/8.
 */
public class CheckTool {

    public static String register(String mobileNumber, String pwd, String confirPwd, String code) {
        if (pwd.equals("") || confirPwd.equals("")) {
            return "密码和确认密码不能为空";
        }
        if (code.equals("")) {
            return "验证码不能为空";
        }
        if (!pwd.equals(confirPwd)) {
            return "密码与验证密码不一致";
        }
        return mobileNumber(mobileNumber);
    }

    public static String mobileNumber(String mobileNumber) {
        if (mobileNumber.equals("")) {
            return "手机号不能为空";
        }
        if (mobileNumber.length() != 11) {
            return "手机位数不正确";
        }
        return null;
    }

    public static String login(String mobileNumber, String password) {
        if (mobileNumber.equals("")) {
            return "手机号不能为空";
        }
        if (password.equals("")) {
            return "密码不能为空";
        }
        return mobileNumber(mobileNumber);
    }
}
