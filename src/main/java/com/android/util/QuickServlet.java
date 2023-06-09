package com.android.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class QuickServlet {
    private final SqlSession sqlSession;

    public QuickServlet() {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        sqlSession = sqlSessionFactory.openSession(true);
    }

    public SqlSession get() {
        return sqlSession;
    }

    public void output(String output, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(output);
        sqlSession.close();
    }
}
