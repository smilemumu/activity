package com.example.activityConfig.entity;

public class RuleConfig {
    private Long id;
    private Long activityId;
    private String hasReward;
    private String rewardDeliveryType;
    private String ruleType;
    private String totalClaimLimitEnabled;
    private Integer totalClaimLimit;
    private String regionLimitEnabled;
    private String mutualExclusiveEnabled;
    private Long mutualExclusiveActivityId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public String getHasReward() { return hasReward; }
    public void setHasReward(String hasReward) { this.hasReward = hasReward; }
    public String getRewardDeliveryType() { return rewardDeliveryType; }
    public void setRewardDeliveryType(String rewardDeliveryType) { this.rewardDeliveryType = rewardDeliveryType; }
    public String getRuleType() { return ruleType; }
    public void setRuleType(String ruleType) { this.ruleType = ruleType; }
    public String getTotalClaimLimitEnabled() { return totalClaimLimitEnabled; }
    public void setTotalClaimLimitEnabled(String totalClaimLimitEnabled) { this.totalClaimLimitEnabled = totalClaimLimitEnabled; }
    public Integer getTotalClaimLimit() { return totalClaimLimit; }
    public void setTotalClaimLimit(Integer totalClaimLimit) { this.totalClaimLimit = totalClaimLimit; }
    public String getRegionLimitEnabled() { return regionLimitEnabled; }
    public void setRegionLimitEnabled(String regionLimitEnabled) { this.regionLimitEnabled = regionLimitEnabled; }
    public String getMutualExclusiveEnabled() { return mutualExclusiveEnabled; }
    public void setMutualExclusiveEnabled(String mutualExclusiveEnabled) { this.mutualExclusiveEnabled = mutualExclusiveEnabled; }
    public Long getMutualExclusiveActivityId() { return mutualExclusiveActivityId; }
    public void setMutualExclusiveActivityId(Long mutualExclusiveActivityId) { this.mutualExclusiveActivityId = mutualExclusiveActivityId; }
}
