package com.example.activitys.chunrihuanxin.entity;

import java.util.Date;

/**
 * 春日焕新活动 - 奖励发放记录实体
 *
 * 业务说明：
 * - 记录用户领取各阶段奖励的详细信息
 * - 支持首借奖励、额度奖励、阶梯奖励等多阶段奖励追
 * - 与 REWARD_CONFIG 中的奖励配置关联
 */
public class SpringRenewalRewardRecord {

    private Long id;
    private Long activityId;          // 活动 ID
    private Long userId;              // 参与用户 ID
    private Long rewardConfigId;      // 关联的奖励配置 ID
    private String rewardType;        // 奖励类型
    private String rewardName;        // 奖励名称
    private String rewardStage;       // 奖励阶段：FIRST_BORROW / CREDIT_LIMIT / LADDER
    private String deliveryStatus;    // 发放状态：PENDING / DELIVERED / FAILED
    private Date deliveryTime;        // 发放时间
    private String remark;            // 备注

    // getters and setters...
}
