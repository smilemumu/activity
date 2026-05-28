package com.example.activityConfig.service;

import com.example.activityConfig.dto.PageResult;
import com.example.activityConfig.entity.RewardClaimRecord;
import com.example.activityConfig.entity.RewardConfig;
import com.example.activityConfig.mapper.RewardClaimRecordMapper;
import com.example.activityConfig.mapper.RewardConfigMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataReportService {

    private final RewardClaimRecordMapper claimRecordMapper;
    private final RewardConfigMapper rewardConfigMapper;

    public DataReportService(RewardClaimRecordMapper claimRecordMapper,
                             RewardConfigMapper rewardConfigMapper) {
        this.claimRecordMapper = claimRecordMapper;
        this.rewardConfigMapper = rewardConfigMapper;
    }

    public Map<String, Object> getRewardStock(Long activityId) {
        List<RewardConfig> rewards = rewardConfigMapper.findByActivityId(activityId);

        int totalStock = 0;
        int remainingStock = 0;
        List<Map<String, Object>> itemList = new ArrayList<>();

        for (RewardConfig reward : rewards) {
            Map<String, Object> item = new HashMap<>();
            item.put("rewardName", reward.getRewardName());
            item.put("rewardType", reward.getRewardType());
            item.put("totalStock", reward.getStock());
            // 剩余库存 = 总库存 - 已领取 (实际项目需查询领取记录表)
            item.put("remainingStock", reward.getStock());

            itemList.add(item);
            totalStock += reward.getStock();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalStock", totalStock);
        result.put("remainingStock", remainingStock);
        result.put("items", itemList);
        return result;
    }

    public Map<String, Object> getRewardClaimCount(Long activityId) {
        int count = claimRecordMapper.countByActivityId(activityId);
        Map<String, Object> result = new HashMap<>();
        result.put("count", count);
        return result;
    }

    public PageResult<RewardClaimRecord> getClaimRecords(Long activityId, String rewardCode, String phone, String customerId, int page, int size) {
        int offset = (page - 1) * size;
        List<RewardClaimRecord> records;
        int total;

        boolean hasFilter = (rewardCode != null && !rewardCode.isEmpty())
                || (phone != null && !phone.isEmpty())
                || (customerId != null && !customerId.isEmpty());

        if (hasFilter) {
            records = claimRecordMapper.searchByPage(activityId, rewardCode, phone, customerId, offset, size);
            total = claimRecordMapper.countBySearch(activityId, rewardCode, phone, customerId);
        } else {
            records = claimRecordMapper.findByPage(activityId, offset, size);
            total = claimRecordMapper.countByActivityId(activityId);
        }

        return new PageResult<>(records, total, page, size);
    }
}
