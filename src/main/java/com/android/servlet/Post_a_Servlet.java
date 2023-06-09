package com.android.servlet;

import com.alibaba.fastjson.JSON;
import com.android.mapper.PostMapper;
import com.android.pojo.Post;
import com.android.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/aPost")
public class Post_a_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(new Date() + ">>> " + "正在请求/aPost");
        String s_id = request.getParameter("id");
        int id = Integer.parseInt(s_id);
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
        Post post = postMapper.selectById(id);

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String postInfo = JSON.toJSONString(post);
        writer.write(postInfo);
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
