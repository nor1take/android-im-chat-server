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
    public Result isFriend(int uid, int friendId) {
        Friend friend = friendService.selectByFriendId(uid, friendId);
        Integer code = friend != null ? Code.GET_OK : Code.GET_ERR;
        String msg = friend != null ? "对方是你的好友" : "对方不是你的好友";
        return new Result(code, friend, msg);
    }

    @RequestMapping("/friendAdd")
    public Result addFrind(int uid, int friendId) {
        Friend friend = friendService.selectByFriendId(uid, friendId);
        if (friend == null) {
            Friend friend1 = new Friend(uid, friendId, new Date());
            Friend friend2 = new Friend(friendId, uid, new Date());
            boolean flag = friendService.addBothSide(friend1, friend2);
            return new Result(
                    flag ? Code.SAVE_OK : Code.SAVE_ERR,
                    flag,
                    "成功添加对方为好友"
            );
        } else {
            return new Result(Code.GET_OK, friend, "对方已经是你的好友");
        }
    }

    @RequestMapping("/friendAll")
    public Result getAllFriends(int uid) {
        List<Friend> friendList = friendService.selectByUid(uid);
        return new Result(
                friendList != null ? Code.GET_OK : Code.GET_ERR,
                friendList
        );
    }
}
