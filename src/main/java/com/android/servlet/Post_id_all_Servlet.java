package com.android.servlet;

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
import java.util.List;

@WebServlet("/allPosts")
public class Post_id_all_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(new Date() + ">>> " + "正在请求/allPosts");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
        List<Post> posts = postMapper.selectAll();

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String idList = "";
        for (Post item : posts) {
            int id = item.getId();
            idList += String.valueOf(id);
            idList += "#"; //1#3#
        }
        writer.write(idList);
        sqlSession.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
