package com.blue.cat.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {


    /**
     * 验证手机号
     * @param hex
     * @return
     */
    public static boolean validatePhone(final String hex) {
        Pattern pattern;
        Matcher matcher;
        String PHONE_PATTERN = "^\\d{11}$";
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }



    /**
     * 验证手机号
     * @param hex
     * @return
     */
    public static boolean validateEmail(final String hex) {
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }

}
