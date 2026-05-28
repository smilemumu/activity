package com.example.activityConfig.dto;

public class LoginResponse {
    private String token;
    private String username;
    private String realName;

    public LoginResponse() {}

    public LoginResponse(String token, String username, String realName) {
        this.token = token;
        this.username = username;
        this.realName = realName;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getRealName() { return realName; }
    public void setRealName(String realName) { this.realName = realName; }
}
