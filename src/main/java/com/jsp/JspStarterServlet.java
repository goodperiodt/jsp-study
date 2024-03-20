package com.jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "hello",
        urlPatterns = "/start" // 모든 패턴에 동작할 수 있도록 /* 로 설정, http://localhost:8181
)
public class JspStarterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Welcome to my web-app");
        resp.sendRedirect("index.jsp");
    }
}
