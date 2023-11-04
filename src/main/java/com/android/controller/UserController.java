package com.android.controller;

import com.android.pojo.User;
import com.android.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users", produces = "application/json; charset=utf-8")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public Result login(String username, String password) {
        User user = userService.selectByName(username);

        if (user == null) {
            return new Result(Code.GET_ERR, null, "该用户名未注册");
        } else {
            User user1 = userService.login(username, password);
            return new Result(
                    user1 != null ? Code.GET_OK : Code.GET_ERR,
                    user1,
                    user1 != null ? "登录成功" : "密码错误"
            );
        }
    }

    @RequestMapping("/register")
    public Result register(String username, String password) {
        User user = userService.selectByName(username);

        if (user != null) {
            return new Result(Code.GET_OK, null, "该用户名已注册");
        } else {
            User user1 = new User(username, password);
            boolean flag = userService.add(user1);
            return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag);
        }
    }
}
