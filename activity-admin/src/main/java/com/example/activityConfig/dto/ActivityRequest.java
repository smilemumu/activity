package com.example.activityConfig.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class ActivityRequest {

    @NotBlank(message = "活动类型不能为空")
    private String activityType;

    @NotBlank(message = "活动标题不能为空")
    private String title;

    private String description;

    @NotNull(message = "开始时间不能为空")
    private Date startTime;

    @NotNull(message = "结束时间不能为空")
    private Date endTime;

    private String coverImage;
    private String status;

    private List<OrgRelItem> orgRels;
    private List<DeptRelItem> deptRels;

    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) { this.startTime = startTime; }
    public Date getEndTime() { return endTime; }
    public void setEndTime(Date endTime) { this.endTime = endTime; }
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<OrgRelItem> getOrgRels() { return orgRels; }
    public void setOrgRels(List<OrgRelItem> orgRels) { this.orgRels = orgRels; }
    public List<DeptRelItem> getDeptRels() { return deptRels; }
    public void setDeptRels(List<DeptRelItem> deptRels) { this.deptRels = deptRels; }

    public static class OrgRelItem {
        private String orgCode;
        private String orgName;
        public String getOrgCode() { return orgCode; }
        public void setOrgCode(String orgCode) { this.orgCode = orgCode; }
        public String getOrgName() { return orgName; }
        public void setOrgName(String orgName) { this.orgName = orgName; }
    }

    public static class DeptRelItem {
        private String deptName;
        private String contactName;
        public String getDeptName() { return deptName; }
        public void setDeptName(String deptName) { this.deptName = deptName; }
        public String getContactName() { return contactName; }
        public void setContactName(String contactName) { this.contactName = contactName; }
    }
}
