package com.android.servlet;

import com.android.mapper.PostMapper;
import com.android.pojo.Post;
import com.android.util.SqlSessionFactoryUtil;
import com.android.util.StringFormat;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/send")
public class Post_send_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(new Date() + ">>> " + "正在请求/send");
        String poster = request.getParameter("poster");
        String label = request.getParameter("label");
        String peopleNum = request.getParameter("peopleNum");
        String body = request.getParameter("body");
        label = StringFormat.trans(label);
        body = StringFormat.trans(body);
        System.out.println(new Date() + ">>> " + "新帖子 " + poster + "/" + label + "/" + peopleNum + "/" + body + "/" + new Date());

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);

        Post post = new Post();
        post.setPoster(Integer.parseInt(poster));
        post.setLabel(label);
        post.setPeopleNum(Integer.parseInt(peopleNum));
        post.setBody(body);
        post.setTime(new Date());

        postMapper.add(post);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("发布成功");
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}