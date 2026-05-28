package com.example.activityConfig.enums;

public enum ActivityStatusEnum {
    DRAFT("DRAFT", "草稿"),
    PUBLISHED("PUBLISHED", "已发布"),
    CLOSED("CLOSED", "已下线");

    private final String code;
    private final String desc;

    ActivityStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
