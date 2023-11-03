package com.android.mapper;

import com.android.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


public interface UserMapper {
    @Select("select * from tb_user where username = #{username}")
    User selectByName(String username);

    @Select("select * from tb_user where username = #{username} and password = #{password}")
    User login(@Param("username") String username, @Param("password") String password);

    //@Insert("insert into tb_user VALUES (null, #{userName}, #{passWord})")
    void add(User user);
}
