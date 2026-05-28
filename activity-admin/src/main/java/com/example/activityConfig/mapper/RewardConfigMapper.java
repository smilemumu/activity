package com.example.activityConfig.mapper;

import com.example.activityConfig.entity.RewardConfig;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RewardConfigMapper {

    @Insert("INSERT INTO REWARD_CONFIG (ACTIVITY_ID, REWARD_TYPE, REWARD_CODE, REWARD_NAME, REWARD_ALIAS, " +
            "REWARD_ICON, STOCK, MAX_CLAIM_TIMES, OCCUPY_WIN_COUNT, CONDITION_JSON) " +
            "VALUES (#{activityId}, #{rewardType}, #{rewardCode}, #{rewardName}, #{rewardAlias}, " +
            "#{rewardIcon}, #{stock}, #{maxClaimTimes}, #{occupyWinCount}, #{conditionJson})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(RewardConfig config);

    @Update("UPDATE REWARD_CONFIG SET REWARD_TYPE=#{rewardType}, REWARD_CODE=#{rewardCode}, " +
            "REWARD_NAME=#{rewardName}, REWARD_ALIAS=#{rewardAlias}, REWARD_ICON=#{rewardIcon}, " +
            "STOCK=#{stock}, MAX_CLAIM_TIMES=#{maxClaimTimes}, OCCUPY_WIN_COUNT=#{occupyWinCount}, " +
            "CONDITION_JSON=#{conditionJson} WHERE ID=#{id}")
    void update(RewardConfig config);

    @Delete("DELETE FROM REWARD_CONFIG WHERE ID = #{id}")
    void deleteById(@Param("id") Long id);

    @Select("SELECT * FROM REWARD_CONFIG WHERE ACTIVITY_ID = #{activityId}")
    List<RewardConfig> findByActivityId(@Param("activityId") Long activityId);

    @Select("SELECT * FROM REWARD_CONFIG WHERE ID = #{id}")
    RewardConfig findById(@Param("id") Long id);
}
