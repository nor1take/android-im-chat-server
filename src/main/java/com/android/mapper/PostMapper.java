package com.android.mapper;

import com.android.pojo.Post;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostMapper {
    void add(Post post);

    @Select("select * from tb_post ORDER BY time DESC")
    List<Post> selectAll();

    @Select("select * from tb_post ORDER BY time DESC limit #{arg0} offset #{arg1}")
    List<Post> selectLimitNum(int limitNum, int offset);

    @Select("select * from tb_post where label = #{label} ORDER BY peopleNum DESC limit 3")
    List<Post> selectByLable(String label);

    @Select("select * from tb_post where id = #{id}")
    Post selectById(int id);
}
