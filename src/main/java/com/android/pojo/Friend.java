package com.android.pojo;

import java.util.Date;

public class Friend {
    private Integer id;
    private Integer uid;
    private Integer friendId;
    private Date addTime;

    public Friend() {
    }

    public Friend(Integer uid, Integer friendId, Date addTime) {
        this.uid = uid;
        this.friendId = friendId;
        this.addTime = addTime;
    }
}
