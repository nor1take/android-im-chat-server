package com.android.service;

import com.android.pojo.Post;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {
    boolean add(Post post);

    List<Post> selectAll();

    List<Post> selectLimitNum(int limitNum, int offset);

    List<Post> selectByLable(String label);

    Post selectById(int id);
}
