package com.example.activityConfig.mapper;

import com.example.activityConfig.entity.ActivityDeptRel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ActivityDeptRelMapper {

    @Insert("INSERT INTO ACTIVITY_DEPT_REL (ACTIVITY_ID, DEPT_NAME, CONTACT_NAME) " +
            "VALUES (#{activityId}, #{deptName}, #{contactName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ActivityDeptRel rel);

    @Delete("DELETE FROM ACTIVITY_DEPT_REL WHERE ACTIVITY_ID = #{activityId}")
    void deleteByActivityId(@Param("activityId") Long activityId);

    @Select("SELECT * FROM ACTIVITY_DEPT_REL WHERE ACTIVITY_ID = #{activityId}")
    List<ActivityDeptRel> findByActivityId(@Param("activityId") Long activityId);
}
