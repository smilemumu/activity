package com.example.activitys.chunrihuanxin.enums;

/**
 * 春日焕新活动 - 奖励阶段枚举
 *
 * 业务说明：
 * - 活动分为多个奖励阶段，用户完成各阶段条件后可领取对应奖励
 * - FIRST_BORROW：首次借款成功
 * - CREDIT_LIMIT：授信额度达到指定门槛
 * - LADDER：阶梯奖励（根据借款金额累计）
 */
public enum SpringStageEnum {

    REGISTER("REGISTER", "注册奖励"),
    REAL_NAME("REAL_NAME", "实名认证奖励"),
    FIRST_BORROW("FIRST_BORROW", "首借奖励"),
    CREDIT_LIMIT("CREDIT_LIMIT", "额度奖励"),
    LADDER("LADDER", "阶梯奖励");

    private final String code;
    private final String desc;

    SpringStageEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}
