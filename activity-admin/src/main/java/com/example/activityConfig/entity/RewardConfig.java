package com.example.activityConfig.entity;

public class RewardConfig {
    private Long id;
    private Long activityId;
    private String rewardType;
    private String rewardCode;
    private String rewardName;
    private String rewardAlias;
    private String rewardIcon;
    private Integer stock;
    private Integer maxClaimTimes;
    private String occupyWinCount;
    private String conditionJson;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public String getRewardType() { return rewardType; }
    public void setRewardType(String rewardType) { this.rewardType = rewardType; }
    public String getRewardCode() { return rewardCode; }
    public void setRewardCode(String rewardCode) { this.rewardCode = rewardCode; }
    public String getRewardName() { return rewardName; }
    public void setRewardName(String rewardName) { this.rewardName = rewardName; }
    public String getRewardAlias() { return rewardAlias; }
    public void setRewardAlias(String rewardAlias) { this.rewardAlias = rewardAlias; }
    public String getRewardIcon() { return rewardIcon; }
    public void setRewardIcon(String rewardIcon) { this.rewardIcon = rewardIcon; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Integer getMaxClaimTimes() { return maxClaimTimes; }
    public void setMaxClaimTimes(Integer maxClaimTimes) { this.maxClaimTimes = maxClaimTimes; }
    public String getOccupyWinCount() { return occupyWinCount; }
    public void setOccupyWinCount(String occupyWinCount) { this.occupyWinCount = occupyWinCount; }
    public String getConditionJson() { return conditionJson; }
    public void setConditionJson(String conditionJson) { this.conditionJson = conditionJson; }
}
