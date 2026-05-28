package com.example.activityConfig.controller;

import com.example.activityConfig.common.Result;
import com.example.activityConfig.entity.PageConfig;
import com.example.activityConfig.service.PageConfigService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity/{activityId}/page-config")
public class PageConfigController {

    private final PageConfigService pageConfigService;

    public PageConfigController(PageConfigService pageConfigService) {
        this.pageConfigService = pageConfigService;
    }

    @GetMapping
    public Result<List<PageConfig>> list(@PathVariable Long activityId) {
        return Result.success(pageConfigService.getByActivityId(activityId));
    }

    @PutMapping
    public Result<?> save(@PathVariable Long activityId, @RequestBody List<PageConfig> configs) {
        pageConfigService.saveConfigs(activityId, configs);
        return Result.success("保存成功");
    }
}
