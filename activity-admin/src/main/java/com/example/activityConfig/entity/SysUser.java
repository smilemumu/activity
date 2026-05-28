package com.example.activityConfig.entity;

public class SysUser {
    private Long id;
    private String username;
    private String password;
    private String realName;
    private String deptName;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
