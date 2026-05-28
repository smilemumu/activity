package com.example.activityConfig.mapper;

import com.example.activityConfig.entity.ActivityProductRel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ActivityProductRelMapper {

    @Insert("INSERT INTO ACTIVITY_PRODUCT_REL (ACTIVITY_ID, PRODUCT_CODE, PRODUCT_NAME) " +
            "VALUES (#{activityId}, #{productCode}, #{productName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ActivityProductRel rel);

    @Delete("DELETE FROM ACTIVITY_PRODUCT_REL WHERE ACTIVITY_ID = #{activityId}")
    void deleteByActivityId(@Param("activityId") Long activityId);

    @Select("SELECT * FROM ACTIVITY_PRODUCT_REL WHERE ACTIVITY_ID = #{activityId}")
    List<ActivityProductRel> findByActivityId(@Param("activityId") Long activityId);
}
