package cn.edu.swu.bolg.controller;

import cn.edu.swu.bolg.dao.Repo;
import cn.edu.swu.bolg.model.po.Blog;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listBlog")
public class ListBlogServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Blog> blogs = Repo.getInstance().getAll();
            response.setContentType("text/html; charset=UTF-8");
            try(Writer writer = response.getWriter()) {
                writer.write("<center style=\"margin-top:5em\">\n");
                writer.write("<h1>欢迎访问我的博客</h1>\n");

                writer.write("<table width='55%' border='0' cellpadding=4>");

                for(int i=0; i<blogs.size(); i++) {
                    Blog blog = blogs.get(i);
                    if (i % 2 == 0) {
                        writer.write("<tr style='background-color:#F5F5F5;height:2em'>");
                    } else {
                        writer.write("<tr style='background-color:#D6E6F2;height:2em'>");
                    }
                    writer.write(String.format("<td width='30px'>%s</td>", blog.getId()));
                    writer.write(String.format("<td width='150px'>%s</td>", blog.getTitle()));
                    writer.write(String.format("<td width='180px'>%s</td>", blog.getStatus()));
                    writer.write(String.format("<td width='60px'>%s</td>", blog.getCreateTime()));
                    writer.write(String.format("<td>%s</td>", blog.getContent()));
                    writer.write(String.format("<td><img src='%s' style='width:50px'/></td>", blog.getImage()));
                    writer.write(String.format("<td><a href='./deleteBlog?id=%s'>" +
                            "<img src='./images/trash.png' width='20px'></a></td>", blog.getId()));
                    writer.write(String.format("<td><a href='./updateBlog?id=%s'>" +
                            "<img src='./images/edit.png' width='20px'></a></td>", blog.getId()));
                    writer.write("</tr>");
                }
                writer.write("</table><br><br>\n\n");

                writer.write("<a href='./admin.html'>返 回 首 页</a>\n");
                writer.write("</center>\n");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
