package com.android.controller;

import com.android.pojo.Friend;
import com.android.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @RequestMapping("/aFriend")
    public String isFriend(int uid, int friendId) {
        Friend friend = friendService.selectByFriendId(uid, friendId);
        if (friend == null) {
            return "n";
        } else {
            return "y";
        }
    }

    @RequestMapping("/friendAdd")
    public String addFrind(int uid, int friendId) {
        Friend friend = friendService.selectByFriendId(uid, friendId);
        if (friend == null) {
            Friend friend1 = new Friend(uid, friendId, new Date());
            Friend friend2 = new Friend(friendId, uid, new Date());
            friendService.add(friend1);
            friendService.add(friend2);
            return "success";
        } else {
            return "exist";
        }
    }

    @RequestMapping("/friendAll")
    public List<Friend> getAllFriends(int uid) {
        return friendService.selectByUid(uid);
    }
}
