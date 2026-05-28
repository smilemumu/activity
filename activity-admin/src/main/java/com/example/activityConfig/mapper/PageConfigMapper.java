package com.example.activityConfig.mapper;

import com.example.activityConfig.entity.PageConfig;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PageConfigMapper {

    @Insert("INSERT INTO PAGE_CONFIG (ACTIVITY_ID, CONFIG_TYPE, CONFIG_KEY, CONFIG_VALUE, WIDTH, HEIGHT, SORT_ORDER) " +
            "VALUES (#{activityId}, #{configType}, #{configKey}, #{configValue}, #{width}, #{height}, #{sortOrder})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(PageConfig config);

    @Delete("DELETE FROM PAGE_CONFIG WHERE ACTIVITY_ID = #{activityId}")
    void deleteByActivityId(@Param("activityId") Long activityId);

    @Select("SELECT * FROM PAGE_CONFIG WHERE ACTIVITY_ID = #{activityId} ORDER BY SORT_ORDER")
    List<PageConfig> findByActivityId(@Param("activityId") Long activityId);
}
