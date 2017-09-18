package com.xiaolan.authority.mapper;

import com.xiaolan.authority.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select(value = "select * from auth_user where username = #{username}")
    User getByUsername(@Param("username") String username);


    User getByUsernameFromMapper(@Param("username") String username);
}