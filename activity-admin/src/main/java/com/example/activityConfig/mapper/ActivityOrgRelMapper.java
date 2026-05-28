package com.example.activityConfig.mapper;

import com.example.activityConfig.entity.ActivityOrgRel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ActivityOrgRelMapper {

    @Insert("INSERT INTO ACTIVITY_ORG_REL (ACTIVITY_ID, ORG_CODE, ORG_NAME) " +
            "VALUES (#{activityId}, #{orgCode}, #{orgName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ActivityOrgRel rel);

    @Delete("DELETE FROM ACTIVITY_ORG_REL WHERE ACTIVITY_ID = #{activityId}")
    void deleteByActivityId(@Param("activityId") Long activityId);

    @Select("SELECT * FROM ACTIVITY_ORG_REL WHERE ACTIVITY_ID = #{activityId}")
    List<ActivityOrgRel> findByActivityId(@Param("activityId") Long activityId);
}
