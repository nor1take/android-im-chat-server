package com.android.servlet;

import com.android.mapper.UserMapper;
import com.android.pojo.User;
import com.android.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/register")
public class A_Register_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(new Date() + ">>> " + "正在请求/register");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(new Date() + ">>> " + "用户输入/username password " + username + password);

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByName(username);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        if (user != null) {
            writer.write("该用户名已注册");
        } else {
            User user1 = new User();
            user1.setUserName(username);
            user1.setPassWord(password);
            userMapper.add(user1);
            writer.write("注册成功");
        }
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
