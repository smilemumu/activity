package com.example.activityConfig.mapper;

import com.example.activityConfig.entity.RuleConfig;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RuleConfigMapper {

    @Insert("INSERT INTO RULE_CONFIG (ACTIVITY_ID, HAS_REWARD, REWARD_DELIVERY_TYPE, RULE_TYPE, " +
            "TOTAL_CLAIM_LIMIT_ENABLED, TOTAL_CLAIM_LIMIT, REGION_LIMIT_ENABLED, " +
            "MUTUAL_EXCLUSIVE_ENABLED, MUTUAL_EXCLUSIVE_ACTIVITY_ID) " +
            "VALUES (#{activityId}, #{hasReward}, #{rewardDeliveryType}, #{ruleType}, " +
            "#{totalClaimLimitEnabled}, #{totalClaimLimit}, #{regionLimitEnabled}, " +
            "#{mutualExclusiveEnabled}, #{mutualExclusiveActivityId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(RuleConfig config);

    @Update("UPDATE RULE_CONFIG SET HAS_REWARD=#{hasReward}, REWARD_DELIVERY_TYPE=#{rewardDeliveryType}, " +
            "TOTAL_CLAIM_LIMIT_ENABLED=#{totalClaimLimitEnabled}, TOTAL_CLAIM_LIMIT=#{totalClaimLimit}, " +
            "REGION_LIMIT_ENABLED=#{regionLimitEnabled}, " +
            "MUTUAL_EXCLUSIVE_ENABLED=#{mutualExclusiveEnabled}, MUTUAL_EXCLUSIVE_ACTIVITY_ID=#{mutualExclusiveActivityId} " +
            "WHERE ACTIVITY_ID=#{activityId}")
    void update(RuleConfig config);

    @Select("SELECT * FROM RULE_CONFIG WHERE ACTIVITY_ID = #{activityId}")
    RuleConfig findByActivityId(@Param("activityId") Long activityId);
}
