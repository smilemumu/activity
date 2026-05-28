package com.example.activityConfig.mapper;

import com.example.activityConfig.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM SYS_USER WHERE USERNAME = #{username}")
    SysUser findByUsername(@Param("username") String username);

    @Select("SELECT * FROM SYS_USER WHERE ID = #{id}")
    SysUser findById(@Param("id") Long id);
}
