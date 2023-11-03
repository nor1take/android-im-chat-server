package com.android.service.impl;

import com.android.mapper.MessageMapper;
import com.android.pojo.Message;
import com.android.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public boolean add(Message message) {
        messageMapper.add(message);
        return true;
    }

    @Override
    public List<String> selectAllChatGroup(int uid) {
        return messageMapper.selectAll_chatGroup(uid);
    }

    @Override
    public List<Message> selectAllMessage(String chatGroup) {
        return messageMapper.selectAll_message(chatGroup);
    }
}
