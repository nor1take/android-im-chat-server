package com.android.mapper;

import com.android.pojo.Message;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MessageMapper {
    void add(Message message);

    @Select("SELECT chatGroup FROM `tb_message` WHERE senderId = #{uid} or receiverId = #{uid} GROUP BY chatGroup")
    List<String> selectAll_chatGroup(int uid);

    @Select("SELECT * FROM `tb_message` WHERE chatGroup = #{chatGroup}")
    List<Message> selectAll_message(String chatGroup);
}
