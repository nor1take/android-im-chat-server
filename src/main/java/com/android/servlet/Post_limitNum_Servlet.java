package com.android.servlet;

import com.alibaba.fastjson.JSON;
import com.android.mapper.PostMapper;
import com.android.pojo.Post;
import com.android.util.QuickServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/limitNumPosts")
public class Post_limitNum_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(new Date() + ">>> " + "正在请求/limitNumPosts");
        String limit = request.getParameter("limit");
        String offset = request.getParameter("offset");

        System.out.println(new Date() + ">>> " + "limit : offset = " + limit + " : " + offset);

        QuickServlet qs = new QuickServlet();
        PostMapper postMapper = qs.get().getMapper(PostMapper.class);
        List<Post> posts = postMapper.selectLimitNum(Integer.parseInt(limit), Integer.parseInt(offset));
        qs.output(JSON.toJSONString(posts), response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
