package com.android.controller;

import com.android.pojo.Message;
import com.android.service.MessageService;
import com.android.util.StringFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/messages", produces = "application/json; charset=utf-8")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/allMyChatGroups")
    public Result getAllMyChatGroups(int uid) {
        List<String> allMyChatGroups = messageService.selectAllChatGroup(uid);
        return new Result(
                allMyChatGroups != null ? Code.GET_OK : Code.GET_ERR,
                allMyChatGroups
        );
    }

    @RequestMapping("/allMsgs")
    public Result getAllMessages(String chatGroup) {
//        chatGroup = StringFormat.trans(chatGroup);
        List<Message> messageList = messageService.selectAllMessage(chatGroup);
        return new Result(
                messageList != null ? Code.GET_OK : Code.GET_ERR,
                messageList
        );
    }

    @RequestMapping("/sendMsg")
    public Result sendMessage(String senderId, String message, String receiverId, String postId, String chatGroup) {
//        message = StringFormat.trans(message);
//        chatGroup = StringFormat.trans(chatGroup);
        Message message1 = new Message(
                null,
                Integer.valueOf(senderId),
                message,
                Integer.valueOf(receiverId),
                new Date(),
                Integer.valueOf(postId),
                chatGroup
        );
        boolean flag = messageService.add(message1);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
    }
}
