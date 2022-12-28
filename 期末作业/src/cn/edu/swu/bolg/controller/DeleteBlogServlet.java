package cn.edu.swu.bolg.controller;

import cn.edu.swu.bolg.dao.Repo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteBlog")
public class DeleteBlogServlet extends HttpServlet  {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        try {
            Repo.getInstance().deleteBlog(Long.valueOf(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("./listBlog");
    }
}
