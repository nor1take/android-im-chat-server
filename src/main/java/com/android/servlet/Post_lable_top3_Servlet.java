package com.android.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.android.mapper.PostMapper;
import com.android.pojo.Post;
import com.android.util.QuickServlet;
import com.android.util.StringFormat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/labelTop3")
public class Post_lable_top3_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(new Date() + ">>> " + "正在请求/labelTop3");
        String label = request.getParameter("label");
        label = StringFormat.trans(label);
        QuickServlet quickServlet = new QuickServlet();
        PostMapper postMapper = quickServlet.get().getMapper(PostMapper.class);
        List<Post> posts = postMapper.selectByLable(label);
        System.out.println(new Date() + posts.toString());

        quickServlet.output(JSON.toJSONString(posts), response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
