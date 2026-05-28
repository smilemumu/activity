package com.example.activityConfig.service;

import com.example.activityConfig.common.BusinessException;
import com.example.activityConfig.entity.*;
import com.example.activityConfig.mapper.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RuleConfigService {

    private final RuleConfigMapper ruleConfigMapper;
    private final ParticipationConditionMapper conditionMapper;
    private final ActivityProductRelMapper productRelMapper;
    private final ActivityMapper activityMapper;

    public RuleConfigService(RuleConfigMapper ruleConfigMapper,
                             ParticipationConditionMapper conditionMapper,
                             ActivityProductRelMapper productRelMapper,
                             ActivityMapper activityMapper) {
        this.ruleConfigMapper = ruleConfigMapper;
        this.conditionMapper = conditionMapper;
        this.productRelMapper = productRelMapper;
        this.activityMapper = activityMapper;
    }

    public RuleConfig getByActivityId(Long activityId) {
        return ruleConfigMapper.findByActivityId(activityId);
    }

    @Transactional
    public void save(Long activityId, RuleConfig config, List<ActivityProductRel> products, List<ParticipationCondition> conditions) {
        if (activityMapper.findById(activityId) == null) {
            throw new BusinessException("活动不存在");
        }

        RuleConfig existing = ruleConfigMapper.findByActivityId(activityId);
        config.setActivityId(activityId);
        if (existing != null) {
            ruleConfigMapper.update(config);
        } else {
            ruleConfigMapper.insert(config);
        }

        productRelMapper.deleteByActivityId(activityId);
        if (products != null) {
            for (ActivityProductRel p : products) {
                p.setActivityId(activityId);
                productRelMapper.insert(p);
            }
        }

        conditionMapper.deleteByActivityId(activityId);
        if (conditions != null) {
            for (ParticipationCondition c : conditions) {
                c.setActivityId(activityId);
                conditionMapper.insert(c);
            }
        }
    }

    public List<ActivityProductRel> getProducts(Long activityId) {
        return productRelMapper.findByActivityId(activityId);
    }

    public List<ParticipationCondition> getConditions(Long activityId) {
        return conditionMapper.findByActivityId(activityId);
    }
}
