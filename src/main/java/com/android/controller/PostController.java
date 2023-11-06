package com.android.controller;

import com.android.pojo.Post;
import com.android.service.PostService;
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
    public Result getAPost(String id) {
        Post post = postService.selectById(Integer.parseInt(id));
        return new Result(
                post != null ? Code.GET_OK : Code.GET_ERR,
                post
        );
    }

    @RequestMapping("/allPosts")
    public Result getAllPostIds() {
        List<Post> postList = postService.selectAll();
        StringBuffer idList = new StringBuffer();
        for (Post post : postList) {
            int id = post.getId();
            idList.append(id);
            idList.append('#'); //1#3#
        }
        return new Result(
                postList != null ? Code.GET_OK : Code.GET_ERR,
                idList.toString()
        );
    }

    @RequestMapping("/labelTop3")
    public Result getLableTop3(String label) {
//        label = StringFormat.trans(label);
        List<Post> postList = postService.selectByLable(label);
        return new Result(
                postList != null ? Code.GET_OK : Code.GET_ERR,
                postList
        );
    }

    @RequestMapping("/limitNumPosts")
    public Result getLimitNumPosts(String limit, String offset) {
        List<Post> postList = postService.selectLimitNum(Integer.parseInt(limit), Integer.parseInt(offset));
        return new Result(
                postList != null ? Code.GET_OK : Code.GET_ERR,
                postList
        );
    }

    @RequestMapping("/send")
    public Result sendPost(String poster, String label, String peopleNum, String body) {
//        label = StringFormat.trans(label);
//        body = StringFormat.trans(body);
        Post post = new Post(
                null,
                Integer.parseInt(poster),
                label,
                Integer.parseInt(peopleNum),
                body,
                new Date()
        );
        boolean flag = postService.add(post);
        return new Result(
                flag ? Code.SAVE_OK : Code.SAVE_ERR,
                flag,
                flag ? "发布成功" : "发布失败"
        );
    }
}
