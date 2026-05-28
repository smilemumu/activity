package com.example.activityConfig.mapper;

import com.example.activityConfig.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {

    int insert(Activity activity);

    int update(Activity activity);

    Activity findById(@Param("id") Long id);

    List<Activity> findByPage(@Param("keyword") String keyword,
                              @Param("status") String status,
                              @Param("offset") int offset,
                              @Param("limit") int limit);

    int countByPage(@Param("keyword") String keyword, @Param("status") String status);
}
