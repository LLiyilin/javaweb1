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

@WebServlet("/updateBlog")
public class UpdateBlogServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Blog blog = null;
        try {
            blog = Repo.getInstance().getById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>My Blog</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <center>\n" +
                "    <div style=\"margin-top:5em; padding: 2em;text-align:center; width:60%; background-color:#EEEEEE\">\n" +
                "      <h2>编辑博客信息</h2>\n" +
                "      <form action=\"./saveBlog\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                "        <input type=\"hidden\" name=\"id\" value=\"" + blog.getId() + "\"><br><br>\n" +
                "        标题： <input type=\"text\" name=\"title\" value=\"" + blog.getTitle() + "\"><br><br>\n" +
                "<span style=\"position: relative;top: -310px\">内 容：</span><textarea name=\"content\" rows=\"22\" cols=\"21\"> "+blog.getContent()+ "</textarea><br><br>"+
                "<span style=\"padding-left:80px\"/>图 片： <input type=\"file\" name=\"picture\" value=\"+"+blog.getImage()+"\"> <br><br>"+
                "是 否 发 布： <input id=\"no\" type=\"radio\" name=\"status\" value=\"0\" "+ ("0".equals(blog.getStatus())?"checked":"") +" ><label for=\"no\">否</label>\n" +
                "        <input id=\"yes\" type=\"radio\" name=\"status\" value=\"1\" " + ("1".equals(blog.getStatus())?"checked":"") + "><label for=\"yes\">是</label><br><br>"+

                "        <input type=\"submit\" value=\" 提 交 信 息\">\n" +
                "      </form>\n" +
                "    </div>\n" +
                "  </center>\n" +
                "</body>\n" +
                "</html>";

        response.setContentType("text/html; Charset=utf8");
        try(Writer writer = response.getWriter()) {
            writer.write(html);
        }
    }

}
