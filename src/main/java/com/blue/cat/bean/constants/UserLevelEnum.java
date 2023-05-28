package com.blue.cat.bean.constants;


import java.util.Objects;

/**
 * 用户的表示
 */
public enum UserLevelEnum {

    COMMON_USER(UserLevelConstant.COMMON_USER,"普通用户"),
    DURATION_USER(UserLevelConstant.DURATION_USER,"时长用户"),
    PERMANENT_USERS(UserLevelConstant.PERMANENT_USERS,"永久用户");

    private String userLevel;
    private String desc;

    UserLevelEnum(String userLevel, String desc) {
        this.userLevel = userLevel;
        this.desc = desc;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据userLevel获取desc
     * @param userLevel
     * @return
     */
    public static String getDescByUserLevel(String userLevel){
        for (UserLevelEnum value : values()) {
            if(Objects.equals(value.getUserLevel(),userLevel)){
                return value.getDesc();
            }
        }
        return "";
    }
}
