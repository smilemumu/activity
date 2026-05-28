package com.example.activitys.chunrihuanxin.dto;

import java.util.List;
import java.util.Map;

/**
 * 春日焕新活动 - 用户参与状态响应 DTO
 *
 * 业务说明：
 * - 返回用户参与活动的完整状态
 * - 包含各阶段完成状态和奖励领取情况
 * - 前端根据此数据渲染活动页面的不同状态
 */
public class SpringRenewalResponse {

    private Long userId;                          // 用户 ID
    private String status;                        // 整体参与状态
    private List<StageProgress> stageProgress;    // 各阶段进度

    /**
     * 阶段进度内部类
     */
    public static class StageProgress {
        private String stageCode;     // 阶段编码
        private String stageName;     // 阶段名称
        private boolean completed;    // 是否完成
        private boolean claimed;      // 是否已领取奖励
        private Map<String, Object> extraInfo;  // 额外信息（如额度金额、借款金额等）
    }
}
