package com.blue.cat.bean.common;

import com.blue.cat.bean.constants.CommonConstant;

public enum BaseCodeEnum implements BaseCode {

    // 成功
    SUCCESS(200, "OK"),
    FAIL(-1, "FAIL"),
    PARAM_ERROR(400, "PARAM VALID NOT PASS!"),
    SIGNATURE_NOT_MATCH(401, "SIGNATURE NOT MATCH!"),
    INTERNAL_SERVER_ERROR(500, "INTERNAL SERVER ERROR!"),
    SERVER_BUSY(503, "系统繁忙，请稍微再试!"),
    NO_VISITS_NUMBER(1002,"您的访问次数已经达到上限，请关注公众号“蓝猫三千问”获取访问次数 " +
            " ![蓝猫三千问]( "+ CommonConstant.OFFICIAL_ACCOUNT +" ) "),
    LOGIN_EXPIRE(1001, "blueCat-login-expire"),
    HAVE_FILTER_WORD(1003, "问题中包含保存违规信息，请重新编辑！"),
    IP_LIMIT_MSG(1006,"您好，不好意思！为防止接口被刷次数，登录账号即可重新访问！"),
    VISIT_BUZY(1008,"您好，不好意思！为防止接口被刷次数，发现您现在访问过于频繁，请等待五秒再进行访问！");
    private int value;

    private String text;

    BaseCodeEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }

    @Override
    public int getCode() {
        return this.value;
    }

    @Override
    public String getMsg() {
        return this.text;
    }
}
