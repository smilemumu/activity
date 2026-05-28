package com.example.activityConfig.controller;

import com.example.activityConfig.common.Result;
import com.example.activityConfig.entity.RewardConfig;
import com.example.activityConfig.service.RewardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity/{activityId}/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping
    public Result<List<RewardConfig>> list(@PathVariable Long activityId) {
        return Result.success(rewardService.listByActivityId(activityId));
    }

    @PostMapping
    public Result<?> create(@PathVariable Long activityId, @RequestBody RewardConfig config) {
        rewardService.create(activityId, config);
        return Result.success("添加成功");
    }

    @PutMapping("/{rewardId}")
    public Result<?> update(@PathVariable Long activityId,
                            @PathVariable Long rewardId,
                            @RequestBody RewardConfig config) {
        rewardService.update(rewardId, config);
        return Result.success("更新成功");
    }

    @DeleteMapping("/{rewardId}")
    public Result<?> delete(@PathVariable Long activityId,
                            @PathVariable Long rewardId) {
        rewardService.delete(rewardId);
        return Result.success("删除成功");
    }
}
