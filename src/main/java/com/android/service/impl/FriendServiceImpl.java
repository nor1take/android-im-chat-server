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
    public boolean addBothSide(Friend friend1, Friend friend2) {
        friendMapper.add(friend1);
        //模拟错误：int a = 1 / 0;
        friendMapper.add(friend2);
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
