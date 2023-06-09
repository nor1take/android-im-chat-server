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

@WebServlet("/login")
public class A_Login_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(new Date() + ">>> " + "正在请求/login");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(new Date() + ">>> " + "用户输入/username password " + username + password);

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByName(username);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        if (user == null) {
            //fail
            writer.write("该用户名未注册");
        } else {
            //success
            User login = userMapper.login(username, password);
            if (login == null) {
                writer.write("密码错误");
            } else {
                int id = user.getId();
                String s_id = String.valueOf(id);
                writer.write("登录成功" + s_id);
            }
        }
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
