package com.android.controller;

import com.alibaba.fastjson.JSONObject;
import com.android.pojo.User;
import com.android.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users", produces = "application/json; charset=utf-8")
public class UserController {
    @Autowired
    private UserService userService;

    private JSONObject jsonObject = new JSONObject();

    @RequestMapping("/login")
    public String login(String username, String password) {
        User user = userService.selectByName(username);

        if (user == null) {
            //fail
            jsonObject.put("state", "该用户名未注册");
            return jsonObject.toString();
        } else {
            //success
            User login = userService.login(username, password);
            if (login == null) {
                jsonObject.put("state", "密码错误");
                return jsonObject.toString();
            } else {
                int id = user.getId();
                jsonObject.put("state", "登录成功");
                jsonObject.put("id", id);
                return jsonObject.toString();
            }
        }
    }

    @RequestMapping("/register")
    public String register(String username, String password) {
        User user = userService.selectByName(username);

        if (user != null) {
            jsonObject.put("state", "该用户名已注册");
            return jsonObject.toString();
        } else {
            User user1 = new User();
            user1.setUserName(username);
            user1.setPassWord(password);
            userService.add(user1);
            jsonObject.put("state", "注册成功");
            return jsonObject.toString();
        }
    }
}
