package com.example.activityConfig.enums;

public enum RewardTypeEnum {
    POINTS("POINTS", "积分"),
    COUPON("COUPON", "优惠券"),
    EXCHANGE_CODE("EXCHANGE_CODE", "兑换码"),
    THANKS("THANKS", "谢谢参与"),
    MALL_COUPON("MALL_COUPON", "商城商品券");

    private final String code;
    private final String desc;

    RewardTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
