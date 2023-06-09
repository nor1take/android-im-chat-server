package com.android.mapper;

import com.android.pojo.Friend;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FriendMapper {
    void add(Friend friend);

    @Select("select * from tb_friend where uid = #{uid} and friendId = #{friendId}")
    Friend selectByFriendId(@Param("uid") int uid, @Param("friendId") int friendId);

    @Select("select * from tb_friend where uid = #{uid}")
    List<Friend> selectByUid(@Param("uid") int uid);
}
