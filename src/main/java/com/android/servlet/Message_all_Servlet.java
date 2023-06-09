package com.android.servlet;

import com.alibaba.fastjson.JSON;
import com.android.mapper.MessageMapper;
import com.android.pojo.Message;
import com.android.util.QuickServlet;
import com.android.util.StringFormat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/allMsgs")
public class Message_all_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(new Date() + ">>> " + "正在请求/allMsgs");
        String chatGroup = request.getParameter("chatGroup");
        chatGroup = StringFormat.trans(chatGroup);
        QuickServlet quickServlet = new QuickServlet();
        MessageMapper messageMapper = quickServlet.get().getMapper(MessageMapper.class);
        List<Message> messageList = messageMapper.selectAll_message(chatGroup);
        System.out.println(new Date() + ">>> " + messageList);
        quickServlet.output(JSON.toJSONString(messageList), response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
