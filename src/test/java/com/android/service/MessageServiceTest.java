package com.android.service;

import com.android.config.SpringConfig;
import com.android.pojo.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;

    @Test
    public void testGetAllMessages() {
        List<Message> messageList = messageService.selectAllMessage(
                "{\"from\":\"3用户\",\"message\":\"...\",\"postId\":25,\"to\":\"6用户\"}"
        );
        System.out.println(messageList);
    }

}
