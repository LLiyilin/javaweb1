package cn.edu.swu.bolg.controller;

import cn.edu.swu.bolg.dao.Repo;
import cn.edu.swu.bolg.model.po.Blog;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/api/blogs")
public class ListBlogApi extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Blog> blogs = Repo.getInstance().getAllStatus();
            response.setContentType("application/json; charset=UTF-8");
            try (Writer writer = response.getWriter()) {
                this.writeJsonByJackson(response.getWriter(), blogs);
            }
        } catch (SQLException e) {
            throw new IOException(e);
        }
    }

    private void writeJsonByJackson(Writer writer, List<Blog> blogs) throws IOException {
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(blogs);
        writer.write(json);
    }
}
