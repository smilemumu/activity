package com.example.activityConfig.controller;

import com.example.activityConfig.common.Result;
import com.example.activityConfig.entity.*;
import com.example.activityConfig.service.RuleConfigService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/activity/{activityId}/rule-config")
public class RuleConfigController {

    private final RuleConfigService ruleConfigService;

    public RuleConfigController(RuleConfigService ruleConfigService) {
        this.ruleConfigService = ruleConfigService;
    }

    @GetMapping
    public Result<Map<String, Object>> detail(@PathVariable Long activityId) {
        Map<String, Object> data = new HashMap<>();
        data.put("ruleConfig", ruleConfigService.getByActivityId(activityId));
        data.put("products", ruleConfigService.getProducts(activityId));
        data.put("conditions", ruleConfigService.getConditions(activityId));
        return Result.success(data);
    }

    @PutMapping
    public Result<?> save(@PathVariable Long activityId,
                          @RequestBody RuleConfigSaveRequest request) {
        ruleConfigService.save(activityId, request.getRuleConfig(),
                request.getProducts(), request.getConditions());
        return Result.success("保存成功");
    }

    public static class RuleConfigSaveRequest {
        private RuleConfig ruleConfig;
        private List<ActivityProductRel> products;
        private List<ParticipationCondition> conditions;

        public RuleConfig getRuleConfig() { return ruleConfig; }
        public void setRuleConfig(RuleConfig ruleConfig) { this.ruleConfig = ruleConfig; }
        public List<ActivityProductRel> getProducts() { return products; }
        public void setProducts(List<ActivityProductRel> products) { this.products = products; }
        public List<ParticipationCondition> getConditions() { return conditions; }
        public void setConditions(List<ParticipationCondition> conditions) { this.conditions = conditions; }
    }
}
