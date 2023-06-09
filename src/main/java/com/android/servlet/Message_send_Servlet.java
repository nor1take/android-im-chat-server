package com.android.servlet;

import com.android.mapper.MessageMapper;
import com.android.pojo.Message;
import com.android.util.QuickServlet;
import com.android.util.StringFormat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/sendMsg")
public class Message_send_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(new Date() + ">>> " + "正在请求/SendMsg");
        String senderId = request.getParameter("senderId");
        String content = request.getParameter("message");
        String receiverId = request.getParameter("receiverId");
        String postId = request.getParameter("postId");
        String chatGroup = request.getParameter("chatGroup");

        content = StringFormat.trans(content);
        chatGroup = StringFormat.trans(chatGroup);

        System.out.println(new Date() + ">>> " + "> " + senderId + " -> " + receiverId + " : " + content + " @ " + new Date() + " # " + postId + " in " + chatGroup);

        QuickServlet qs = new QuickServlet();
        MessageMapper messageMapper = qs.get().getMapper(MessageMapper.class);

        Message message = new Message();
        message.setSenderId(Integer.valueOf(senderId));
        message.setMessage(content);
        message.setReceiverId(Integer.valueOf(receiverId));
        message.setSendTime(new Date());
        message.setPostId(Integer.valueOf(postId));
        message.setChatGroup(chatGroup);

        messageMapper.add(message);

        qs.output("发送成功", response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
