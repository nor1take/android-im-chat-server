package com.android.service.impl;

import com.android.mapper.FriendMapper;
import com.android.pojo.Friend;
import com.android.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendMapper friendMapper;

    @Override
    public boolean add(Friend friend) {
        friendMapper.add(friend);
        return true;
    }

    @Override
    public Friend selectByFriendId(int uid, int friendId) {
        return friendMapper.selectByFriendId(uid, friendId);
    }

    @Override
    public List<Friend> selectByUid(int uid) {
        return friendMapper.selectByUid(uid);
    }
}
