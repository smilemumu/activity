package com.example.activityConfig.service;

import com.example.activityConfig.common.BusinessException;
import com.example.activityConfig.entity.ShareConfig;
import com.example.activityConfig.mapper.ActivityMapper;
import com.example.activityConfig.mapper.ShareConfigMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShareConfigService {

    private final ShareConfigMapper shareConfigMapper;
    private final ActivityMapper activityMapper;

    public ShareConfigService(ShareConfigMapper shareConfigMapper, ActivityMapper activityMapper) {
        this.shareConfigMapper = shareConfigMapper;
        this.activityMapper = activityMapper;
    }

    public ShareConfig getByActivityId(Long activityId) {
        return shareConfigMapper.findByActivityId(activityId);
    }

    @Transactional
    public void save(Long activityId, ShareConfig config) {
        if (activityMapper.findById(activityId) == null) {
            throw new BusinessException("活动不存在");
        }

        ShareConfig existing = shareConfigMapper.findByActivityId(activityId);
        config.setActivityId(activityId);
        if (existing != null) {
            shareConfigMapper.update(config);
        } else {
            shareConfigMapper.insert(config);
        }
    }
}
