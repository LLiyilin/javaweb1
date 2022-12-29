package cn.edu.swu.bolg.controller;


import cn.edu.swu.bolg.dao.Repo;
import cn.edu.swu.user.User;
import cn.edu.swu.user.UserRepo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/api/addUser")
public class AddUserServlet  extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String userName = request.getParameter("user");
        String password = request.getParameter("password");
        User user = new User();
        user.setUser(userName);
        user.setName(name);
        user.setPassword(password);
        try {
            UserRepo.getInstance().save(user);
            System.out.println("提交信息保存成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("提交信息保存失败！");
        }
        response.setContentType("text/html; charset=UTF-8");

        try (Writer writer = response.getWriter()) {
            writer.write("注册成功");
            writer.write("<br><br>");
            writer.write("<a href='"+request.getServletContext().getContextPath()+"/login.html'>返回登录页面</a>");
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
