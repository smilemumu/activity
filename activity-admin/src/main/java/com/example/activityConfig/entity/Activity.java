package com.example.activityConfig.entity;

import java.util.Date;

public class Activity {
    private Long id;
    private String activityType;
    private String title;
    private String description;
    private Date startTime;
    private Date endTime;
    private String coverImage;
    private String status;
    private String createdBy;
    private Date createdTime;
    private Date updatedTime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public Date getCreatedTime() { return createdTime; }
    public void setCreatedTime(Date createdTime) { this.createdTime = createdTime; }
    public Date getUpdatedTime() { return updatedTime; }
    public void setUpdatedTime(Date updatedTime) { this.updatedTime = updatedTime; }
}
