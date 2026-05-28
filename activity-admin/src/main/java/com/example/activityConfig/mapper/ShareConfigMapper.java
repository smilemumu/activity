package com.example.activityConfig.mapper;

import com.example.activityConfig.entity.ShareConfig;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ShareConfigMapper {

    @Insert("INSERT INTO SHARE_CONFIG (ACTIVITY_ID, SHARE_TITLE, SHARE_DESC, SHARE_ICON) " +
            "VALUES (#{activityId}, #{shareTitle}, #{shareDesc}, #{shareIcon})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(ShareConfig config);

    @Update("UPDATE SHARE_CONFIG SET SHARE_TITLE=#{shareTitle}, SHARE_DESC=#{shareDesc}, " +
            "SHARE_ICON=#{shareIcon} WHERE ACTIVITY_ID=#{activityId}")
    void update(ShareConfig config);

    @Select("SELECT * FROM SHARE_CONFIG WHERE ACTIVITY_ID = #{activityId}")
    ShareConfig findByActivityId(@Param("activityId") Long activityId);
}
