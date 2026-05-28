package com.example.activityConfig.entity;

public class ActivityOrgRel {
    private Long id;
    private Long activityId;
    private String orgCode;
    private String orgName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public String getOrgCode() { return orgCode; }
    public void setOrgCode(String orgCode) { this.orgCode = orgCode; }
    public String getOrgName() { return orgName; }
    public void setOrgName(String orgName) { this.orgName = orgName; }
}
