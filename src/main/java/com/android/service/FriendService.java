package com.android.service;

import com.android.pojo.Friend;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FriendService {
    boolean add(Friend friend);

    Friend selectByFriendId(int uid, int friendId);

    List<Friend> selectByUid(int uid);
}
