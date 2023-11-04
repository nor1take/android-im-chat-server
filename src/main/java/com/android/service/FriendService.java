package com.android.service;

import com.android.pojo.Friend;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendService {
    boolean add(Friend friend);

    @Transactional
    boolean addBothSide(Friend friend1, Friend friend2);

    Friend selectByFriendId(int uid, int friendId);

    List<Friend> selectByUid(int uid);
}
