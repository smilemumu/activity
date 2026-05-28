package com.example.activityConfig.controller;

import com.example.activityConfig.common.Result;
import com.example.activityConfig.dto.PageResult;
import com.example.activityConfig.entity.RewardClaimRecord;
import com.example.activityConfig.service.DataReportService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class DataReportController {

    private final DataReportService dataReportService;

    public DataReportController(DataReportService dataReportService) {
        this.dataReportService = dataReportService;
    }

    @GetMapping("/pv-uv")
    public Result<Map<String, Object>> pvUv(@RequestParam Long activityId) {
        // PV/UV 实际需要对接埋点系统，这里返回展示结构
        return Result.success(java.util.Collections.emptyMap());
    }

    @GetMapping("/reward-stock")
    public Result<Map<String, Object>> rewardStock(@RequestParam Long activityId) {
        return Result.success(dataReportService.getRewardStock(activityId));
    }

    @GetMapping("/reward-claim-count")
    public Result<Map<String, Object>> rewardClaimCount(@RequestParam Long activityId) {
        return Result.success(dataReportService.getRewardClaimCount(activityId));
    }

    @GetMapping("/claim-records")
    public Result<PageResult<RewardClaimRecord>> claimRecords(
            @RequestParam Long activityId,
            @RequestParam(required = false) String rewardCode,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String customerId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return Result.success(dataReportService.getClaimRecords(activityId, rewardCode, phone, customerId, page, size));
    }
}
