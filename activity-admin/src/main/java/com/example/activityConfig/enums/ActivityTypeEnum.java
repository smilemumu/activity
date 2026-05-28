package com.example.activityConfig.enums;

public enum ActivityTypeEnum {
    PAGE_REGISTER("PAGE_REGISTER", "页面注册"),
    POPUP_REGISTER("POPUP_REGISTER", "弹窗注册");

    private final String code;
    private final String desc;

    ActivityTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
