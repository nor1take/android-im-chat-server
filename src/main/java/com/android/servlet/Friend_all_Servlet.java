package com.android.servlet;

import com.alibaba.fastjson.JSON;
import com.android.mapper.FriendMapper;
import com.android.pojo.Friend;
import com.android.util.QuickServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/friendAll")
public class Friend_all_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int uid = Integer.parseInt(request.getParameter("uid"));
        QuickServlet quickServlet = new QuickServlet();
        FriendMapper friendMapper = quickServlet.get().getMapper(FriendMapper.class);

        List<Friend> friends = friendMapper.selectByUid(uid);
        quickServlet.output(JSON.toJSONString(friends), response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
