package com.example.activitys.chunrihuanxin.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 春日焕新活动 - 用户注册请求 DTO
 *
 * 业务说明：
 * - 接收用户在活动页面提交的注册信息
 * - 手机号格式校验
 * - 邀请码为可选项（支持裂变邀请玩法）
 */
public class SpringRenewalRequest {

    @NotBlank(message = "姓名不能为空")
    private String realName;

    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @NotBlank(message = "身份证号不能为空")
    private String idCard;

    private String inviteCode;        // 邀请码（可选）

    private String channel;           // 渠道来源

    // getters and setters...
}
