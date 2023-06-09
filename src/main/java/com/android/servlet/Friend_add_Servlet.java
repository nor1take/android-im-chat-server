package com.android.servlet;

import com.android.mapper.FriendMapper;
import com.android.pojo.Friend;
import com.android.pojo.User;
import com.android.util.QuickServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/friendAdd")
public class Friend_add_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(new Date() + ">>> " + "正在请求/friendAdd");
        int uid = Integer.parseInt(request.getParameter("uid"));
        int friendId = Integer.parseInt(request.getParameter("friendId"));

        QuickServlet quickServlet = new QuickServlet();
        FriendMapper friendMapper = quickServlet.get().getMapper(FriendMapper.class);
        Friend friend0 = friendMapper.selectByFriendId(uid, friendId);
        if (friend0 == null) {
            Friend friend1 = new Friend(uid, friendId, new Date());
            Friend friend2 = new Friend(friendId, uid, new Date());
            friendMapper.add(friend1);
            friendMapper.add(friend2);
            quickServlet.output("success", response);
        } else {
            quickServlet.output("exist", response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
