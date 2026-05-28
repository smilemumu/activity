package com.example.activitys.chunrihuanxin.entity;

import java.util.Date;

/**
 * 春日焕新活动 - 用户参与实体
 *
 * 业务说明：
 * - 记录参与春日焕新注册活动的用户信息
 * - 存储用户在活动页面填写的注册资料
 * - 关联奖励发放和领取状态
 */
public class SpringRenewalUser {

    private Long id;
    private Long activityId;          // 关联的活动 ID
    private String customerId;        // 客户 ID（实名认证后回填）
    private String realName;          // 真实姓名
    private String phone;             // 手机号
    private String idCard;            // 身份证号
    private String inviteCode;        // 邀请码（如果有邀请人）
    private String registerChannel;   // 注册渠道（H5 / 小程序 / APP）
    private String status;            // 参与状态：PENDING(待实名) / SUCCESS(已实名) / REJECTED(已拒绝)
    private Date createTime;          // 参与时间
    private Date authTime;            // 实名认证通过时间

    // getters and setters...
}
