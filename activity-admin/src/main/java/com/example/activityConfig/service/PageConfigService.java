package com.example.activityConfig.service;

import com.example.activityConfig.common.BusinessException;
import com.example.activityConfig.entity.PageConfig;
import com.example.activityConfig.mapper.ActivityMapper;
import com.example.activityConfig.mapper.PageConfigMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PageConfigService {

    private final PageConfigMapper pageConfigMapper;
    private final ActivityMapper activityMapper;

    public PageConfigService(PageConfigMapper pageConfigMapper, ActivityMapper activityMapper) {
        this.pageConfigMapper = pageConfigMapper;
        this.activityMapper = activityMapper;
    }

    public List<PageConfig> getByActivityId(Long activityId) {
        return pageConfigMapper.findByActivityId(activityId);
    }

    @Transactional
    public void saveConfigs(Long activityId, List<PageConfig> configs) {
        if (activityMapper.findById(activityId) == null) {
            throw new BusinessException("活动不存在");
        }

        pageConfigMapper.deleteByActivityId(activityId);

        if (configs != null) {
            int sort = 0;
            for (PageConfig config : configs) {
                config.setActivityId(activityId);
                config.setSortOrder(sort++);
                pageConfigMapper.insert(config);
            }
        }
    }
}
