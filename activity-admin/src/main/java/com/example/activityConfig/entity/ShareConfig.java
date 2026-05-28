package com.example.activityConfig.entity;

public class ShareConfig {
    private Long id;
    private Long activityId;
    private String shareTitle;
    private String shareDesc;
    private String shareIcon;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getActivityId() { return activityId; }
    public void setActivityId(Long activityId) { this.activityId = activityId; }
    public String getShareTitle() { return shareTitle; }
    public void setShareTitle(String shareTitle) { this.shareTitle = shareTitle; }
    public String getShareDesc() { return shareDesc; }
    public void setShareDesc(String shareDesc) { this.shareDesc = shareDesc; }
    public String getShareIcon() { return shareIcon; }
    public void setShareIcon(String shareIcon) { this.shareIcon = shareIcon; }
}
