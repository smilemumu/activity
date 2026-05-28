package com.example.activityConfig.entity;

import java.util.Date;

public class RewardClaimRecord {
    private Long id;
    private Long activityId;
    private String customerId;
    private String phone;
    private String realName;
    private String rewardCode;
    private String rewardName;
    private Date claimTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
    public String getRewardCode() { return rewardCode; }
    public void setRewardCode(String rewardCode) { this.rewardCode = rewardCode; }
    public String getRewardName() { return rewardName; }
    public void setRewardName(String rewardName) { this.rewardName = rewardName; }
    public Date getClaimTime() { return claimTime; }
    public void setClaimTime(Date claimTime) { this.claimTime = claimTime; }
}
