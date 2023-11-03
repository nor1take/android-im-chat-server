package com.android.service;

import com.android.config.SpringConfig;
import com.android.pojo.Friend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class FriendServiceTest {

    @Autowired
    private FriendService friendService;

    @Test
    public void testFriendAll() {
        List<Friend> friends = friendService.selectByUid(3);
        for (Friend friend : friends) {
            System.out.println(friend);
        }
    }

}
