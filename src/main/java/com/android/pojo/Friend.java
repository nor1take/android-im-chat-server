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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", uid=" + uid +
                ", friendId=" + friendId +
                ", addTime=" + addTime +
                '}';
    }
}
