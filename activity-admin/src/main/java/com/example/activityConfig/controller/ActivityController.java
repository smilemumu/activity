package com.example.activityConfig.controller;

import com.example.activityConfig.common.Result;
import com.example.activityConfig.dto.*;
import com.example.activityConfig.service.ActivityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping
    public Result<Long> create(@Valid @RequestBody ActivityRequest request) {
        String createdBy = getCurrentUsername();
        Long id = activityService.create(request, createdBy);
        return Result.success("创建成功", id);
    }

    @PutMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @Valid @RequestBody ActivityRequest request) {
        activityService.update(id, request);
        return Result.success("更新成功");
    }

    @GetMapping("/{id}")
    public Result<ActivityResponse> detail(@PathVariable Long id) {
        return Result.success(activityService.getDetail(id));
    }

    @GetMapping("/page")
    public Result<PageResult<ActivityResponse>> page(ActivityPageRequest request) {
        return Result.success(activityService.page(request));
    }

    @PutMapping("/{id}/status")
    public Result<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
        activityService.updateStatus(id, status);
        return Result.success("状态更新成功");
    }

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getName() : "system";
    }
}
