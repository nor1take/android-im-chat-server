package com.android.controller;

import com.android.pojo.Post;
import com.android.service.PostService;
import com.android.util.StringFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts", produces = "application/json; charset=utf-8")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("/aPost")
    public Post getAPost(String id) {
        return postService.selectById(Integer.parseInt(id));
    }

    @RequestMapping("/allPosts")
    public String getAllPostIds() {
        List<Post> posts = postService.selectAll();
        StringBuffer idList = new StringBuffer();
        for (Post item : posts) {
            int id = item.getId();
            idList.append(id);
            idList.append('#'); //1#3#
        }
        return idList.toString();
    }

    @RequestMapping("/labelTop3")
    public List<Post> getLableTop3(String label) {
        label = StringFormat.trans(label);
        return postService.selectByLable(label);
    }

    @RequestMapping("/limitNumPosts")
    public List<Post> getLimitNumPosts(String limit, String offset) {
        return postService.selectLimitNum(Integer.parseInt(limit), Integer.parseInt(offset));
    }

    @RequestMapping("/send")
    public String sendPost(String poster, String label, String peopleNum, String body) {
        label = StringFormat.trans(label);
        body = StringFormat.trans(body);
        Post post = new Post(
                null,
                Integer.parseInt(poster),
                label,
                Integer.parseInt(peopleNum),
                body,
                new Date()
        );
        postService.add(post);
        return "发布成功";
    }
}
