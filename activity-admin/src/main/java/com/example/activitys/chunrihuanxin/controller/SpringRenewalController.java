package com.example.activitys.chunrihuanxin.controller;

import com.example.activityConfig.common.Result;
import com.example.activitys.chunrihuanxin.service.SpringRenewalService;
import org.springframework.web.bind.annotation.*;

/**
 * 春日焕新活动 - 对外 API 接口
 *
 * 接口列表：
 * - POST /api/activity/chunrihuanxin/{activityId}/register   用户注册参与
 * - GET  /api/activity/chunrihuanxin/{activityId}/status     查询用户参与状态
 * - POST /api/activity/chunrihuanxin/{activityId}/claim       领取阶段奖励
 * - GET  /api/activity/chunrihuanxin/{activityId}/invite-code 获取邀请码
 * - GET  /api/activity/chunrihuanxin/{activityId}/page-config 获取活动页面配置
 */
@RestController
@RequestMapping("/api/activity/chunrihuanxin/{activityId}")
public class SpringRenewalController {

    private final SpringRenewalService springRenewalService;

    public SpringRenewalController(SpringRenewalService springRenewalService) {
        this.springRenewalService = springRenewalService;
    }

    @PostMapping("/register")
    public Result<?> register(@PathVariable Long activityId, @RequestBody Object request) {
        springRenewalService.register(activityId, request);
        return Result.success("参与成功");
    }

    @GetMapping("/status")
    public Result<Object> status(@PathVariable Long activityId, @RequestParam String phone) {
        return Result.success(springRenewalService.getUserStatus(activityId, phone));
    }

    @PostMapping("/claim")
    public Result<?> claimReward(@PathVariable Long activityId,
                                  @RequestParam Long userId,
                                  @RequestParam String stageCode) {
        springRenewalService.claimReward(userId, stageCode);
        return Result.success("领取成功");
    }

    @GetMapping("/invite-code")
    public Result<String> inviteCode(@RequestParam Long userId) {
        return Result.success(springRenewalService.generateInviteCode(userId));
    }
}
