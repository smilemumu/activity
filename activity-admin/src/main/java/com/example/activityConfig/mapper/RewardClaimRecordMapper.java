package com.example.activityConfig.mapper;

import com.example.activityConfig.entity.RewardClaimRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RewardClaimRecordMapper {

    @Select("SELECT COUNT(*) FROM REWARD_CLAIM_RECORD WHERE ACTIVITY_ID = #{activityId}")
    int countByActivityId(@Param("activityId") Long activityId);

    @Select("SELECT * FROM REWARD_CLAIM_RECORD " +
            "WHERE ACTIVITY_ID = #{activityId} ORDER BY CLAIM_TIME DESC " +
            "LIMIT #{offset}, #{limit}")
    List<RewardClaimRecord> findByPage(@Param("activityId") Long activityId,
                                       @Param("offset") int offset,
                                       @Param("limit") int limit);

    @Select("<script>" +
            "SELECT * FROM REWARD_CLAIM_RECORD " +
            "WHERE ACTIVITY_ID = #{activityId}" +
            "<if test='rewardCode != null and rewardCode != \"\"'> AND REWARD_CODE = #{rewardCode}</if>" +
            "<if test='phone != null and phone != \"\"'> AND PHONE = #{phone}</if>" +
            "<if test='customerId != null and customerId != \"\"'> AND CUSTOMER_ID = #{customerId}</if>" +
            " ORDER BY CLAIM_TIME DESC " +
            "LIMIT #{offset}, #{limit}" +
            "</script>")
    List<RewardClaimRecord> searchByPage(@Param("activityId") Long activityId,
                                         @Param("rewardCode") String rewardCode,
                                         @Param("phone") String phone,
                                         @Param("customerId") String customerId,
                                         @Param("offset") int offset,
                                         @Param("limit") int limit);

    @Select("<script>" +
            "SELECT COUNT(*) FROM REWARD_CLAIM_RECORD " +
            "WHERE ACTIVITY_ID = #{activityId}" +
            "<if test='rewardCode != null and rewardCode != \"\"'> AND REWARD_CODE = #{rewardCode}</if>" +
            "<if test='phone != null and phone != \"\"'> AND PHONE = #{phone}</if>" +
            "<if test='customerId != null and customerId != \"\"'> AND CUSTOMER_ID = #{customerId}</if>" +
            "</script>")
    int countBySearch(@Param("activityId") Long activityId,
                      @Param("rewardCode") String rewardCode,
                      @Param("phone") String phone,
                      @Param("customerId") String customerId);
}
