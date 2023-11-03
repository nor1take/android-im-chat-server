package com.android.service.impl;

import com.android.mapper.PostMapper;
import com.android.pojo.Post;
import com.android.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public boolean add(Post post) {
        postMapper.add(post);
        return true;
    }

    @Override
    public List<Post> selectAll() {
        return postMapper.selectAll();
    }

    @Override
    public List<Post> selectLimitNum(int limitNum, int offset) {
        return postMapper.selectLimitNum(limitNum, offset);
    }

    @Override
    public List<Post> selectByLable(String label) {
        return postMapper.selectByLable(label);
    }

    @Override
    public Post selectById(int id) {
        return postMapper.selectById(id);
    }
}
