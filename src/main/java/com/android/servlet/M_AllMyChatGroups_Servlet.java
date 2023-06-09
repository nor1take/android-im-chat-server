package com.android.servlet;

import com.alibaba.fastjson.JSON;
import com.android.mapper.MessageMapper;
import com.android.util.QuickServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/allMyChatGroups")
public class M_AllMyChatGroups_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(new Date() + ">>> " + "正在请求/allMyChatGroups");
        String uid = request.getParameter("uid");

        QuickServlet qs = new QuickServlet();
        MessageMapper messageMapper = qs.get().getMapper(MessageMapper.class);
        List<String> allMyChatGroups = messageMapper.selectAll_chatGroup(Integer.parseInt(uid));
        System.out.println(new Date() + ">>> " + allMyChatGroups);
        qs.output(JSON.toJSONString(allMyChatGroups), response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
