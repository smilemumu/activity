package com.example.activityConfig.entity;

import java.util.List;

public class ParticipationCondition {
    private Long id;
    private Long activityId;
    private String conditionType;
    private String productFilter;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public String getConditionType() { return conditionType; }
    public void setConditionType(String conditionType) { this.conditionType = conditionType; }
    public String getProductFilter() { return productFilter; }
    public void setProductFilter(String productFilter) { this.productFilter = productFilter; }
}
