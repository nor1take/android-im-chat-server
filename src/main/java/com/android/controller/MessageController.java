package com.android.controller;

import com.android.pojo.Message;
import com.android.service.MessageService;
import com.android.util.StringFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/messages", produces = "application/json; charset=utf-8")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/allMyChatGroups")
    public List<String> getAllMyChatGroups(int uid) {
        List<String> allMyChatGroups = messageService.selectAllChatGroup(uid);
        System.out.println(new Date() + ">>> " + allMyChatGroups);
        return allMyChatGroups;
    }

    @RequestMapping("/allMsgs")
    public List<Message> getAllMessages(String chatGroup) {
        chatGroup = StringFormat.trans(chatGroup);
        List<Message> messageList = messageService.selectAllMessage(chatGroup);
        System.out.println(new Date() + ">>> " + messageList);
        return messageList;
    }

    @RequestMapping("/sendMsg")
    public String sendMessage(String senderId, String message, String receiverId, String postId, String chatGroup) {
        message = StringFormat.trans(message);
        chatGroup = StringFormat.trans(chatGroup);
        Message message1 = new Message(
                null,
                Integer.valueOf(senderId),
                message,
                Integer.valueOf(receiverId),
                new Date(),
                Integer.valueOf(postId),
                chatGroup
        );
        System.out.println(new Date() + ">>> " + message1.toString());
        messageService.add(message1);
        return "发送成功";
    }
}
