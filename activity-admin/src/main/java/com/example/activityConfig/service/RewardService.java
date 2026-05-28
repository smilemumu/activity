package com.example.activityConfig.service;

import com.example.activityConfig.common.BusinessException;
import com.example.activityConfig.entity.RewardConfig;
import com.example.activityConfig.mapper.ActivityMapper;
import com.example.activityConfig.mapper.RewardConfigMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardService {

    private final RewardConfigMapper rewardConfigMapper;
    private final ActivityMapper activityMapper;

    public RewardService(RewardConfigMapper rewardConfigMapper, ActivityMapper activityMapper) {
        this.rewardConfigMapper = rewardConfigMapper;
        this.activityMapper = activityMapper;
    }

    public List<RewardConfig> listByActivityId(Long activityId) {
        return rewardConfigMapper.findByActivityId(activityId);
    }

    public void create(Long activityId, RewardConfig config) {
        if (activityMapper.findById(activityId) == null) {
            throw new BusinessException("活动不存在");
        }
        config.setActivityId(activityId);
        rewardConfigMapper.insert(config);
    }

    public void update(Long rewardId, RewardConfig config) {
        RewardConfig existing = rewardConfigMapper.findById(rewardId);
        if (existing == null) {
            throw new BusinessException("奖励配置不存在");
        }
        config.setId(rewardId);
        rewardConfigMapper.update(config);
    }

    public void delete(Long rewardId) {
        if (rewardConfigMapper.findById(rewardId) == null) {
            throw new BusinessException("奖励配置不存在");
        }
        rewardConfigMapper.deleteById(rewardId);
    }
}
