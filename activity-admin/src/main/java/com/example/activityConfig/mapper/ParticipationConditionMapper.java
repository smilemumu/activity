package com.example.activityConfig.mapper;

import com.example.activityConfig.entity.ParticipationCondition;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ParticipationConditionMapper {

    @Insert("INSERT INTO PARTICIPATION_CONDITION (ACTIVITY_ID, CONDITION_TYPE, PRODUCT_FILTER) " +
            "VALUES (#{activityId}, #{conditionType}, #{productFilter})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ParticipationCondition condition);

    @Delete("DELETE FROM PARTICIPATION_CONDITION WHERE ACTIVITY_ID = #{activityId}")
    void deleteByActivityId(@Param("activityId") Long activityId);

    @Select("SELECT * FROM PARTICIPATION_CONDITION WHERE ACTIVITY_ID = #{activityId}")
    List<ParticipationCondition> findByActivityId(@Param("activityId") Long activityId);
}
