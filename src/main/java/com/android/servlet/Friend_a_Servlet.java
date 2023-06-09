package com.android.servlet;

import com.android.mapper.FriendMapper;
import com.android.pojo.Friend;
import com.android.util.QuickServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/aFriend")
public class Friend_a_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(new Date() + ">>> " + "正在请求/aFriend");
        int uid = Integer.parseInt(request.getParameter("uid"));
        int friendId = Integer.parseInt(request.getParameter("friendId"));

        QuickServlet quickServlet = new QuickServlet();
        FriendMapper friendMapper = quickServlet.get().getMapper(FriendMapper.class);
        Friend friend = friendMapper.selectByFriendId(uid, friendId);
        if (friend == null) {
            System.out.println(new Date() + ">>> " + "用户 " + friendId + " 不是" + "用户 " + uid + " 的好友");
            quickServlet.output("n", response);
        } else {
            System.out.println(new Date() + ">>> " + "用户 " + friendId + " 是" + "用户 " + uid + " 的好友");
            quickServlet.output("y", response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
