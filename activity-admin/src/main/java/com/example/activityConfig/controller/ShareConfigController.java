package com.example.activityConfig.controller;

import com.example.activityConfig.common.Result;
import com.example.activityConfig.entity.ShareConfig;
import com.example.activityConfig.service.ShareConfigService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activity/{activityId}/share-config")
public class ShareConfigController {

    private final ShareConfigService shareConfigService;

    public ShareConfigController(ShareConfigService shareConfigService) {
        this.shareConfigService = shareConfigService;
    }

    @GetMapping
    public Result<ShareConfig> detail(@PathVariable Long activityId) {
        return Result.success(shareConfigService.getByActivityId(activityId));
    }

    @PutMapping
    public Result<?> save(@PathVariable Long activityId, @RequestBody ShareConfig config) {
        shareConfigService.save(activityId, config);
        return Result.success("保存成功");
    }
}
