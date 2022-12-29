package cn.edu.swu;

<<<<<<< HEAD
import cn.edu.swu.book.Book;
import cn.edu.swu.book.BookRepo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
=======
import cn.edu.swu.bolg.model.po.Blog;
import cn.edu.swu.bolg.model.po.Book;
import cn.edu.swu.bolg.dao.Repo;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
>>>>>>> ff52b43 (期末修改2)

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

<<<<<<< HEAD
@WebServlet("/index.html")
public class Index extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Book> books = null;
        try {
            books = BookRepo.getInstance().getAll();
=======
@WebServlet("/index-old.html")
public class Index extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Blog> blogs = null;
        try {
            blogs = Repo.getInstance().getAll();
>>>>>>> ff52b43 (期末修改2)
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<br><div class='book-group'>\n");
<<<<<<< HEAD
        for (Book book : books) {
            sb.append("<div class='book-div'>");
            sb.append("<div class='book-pic'><img src=\"./images/b11.jpg\"/></div>");
            sb.append("<div class='book-name'>" + book.getName() + "</div>");
            sb.append("<div class='book-author'>作者: " + book.getAuthor() + "</div>");
            sb.append("<div class='book-price'>价格：" + book.getPrice()+ "</div>");
=======
        for (Blog blog : blogs) {
            sb.append("<div class='book-div'>");
            sb.append("<div class='book-pic'><img src=\"./upload/" + blog.getImage() + "\"/></div>");
            sb.append("<div class='book-name'>" + blog.getTitle() + "</div>");
            sb.append("<div class='book-author'>作者: " + blog.getContent() + "</div>");
            sb.append("<div class='book-price'>" + blog.getId()+ "</div>");
>>>>>>> ff52b43 (期末修改2)
            sb.append("</div>\n");
        }
        sb.append("</div><br>\n");

        String page = "<html>\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <title>My Java Web APP</title>\n" +
                "        <link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\">" +
                "    </head>\n" +
                "    <body>\n" +
                "        <center style=\"margin-top:1em\">\n" +
<<<<<<< HEAD
                "            <h1>欢迎访问我的网上书店</h1>\n" +
                "            <div style=\"margin-top:2em; width: 50%\">\n" +
                "                <div>\n" +
                "                    <div style=\"float:left;padding-right:3em\"><a href=\"./listBook\">查看图书</a></div>\n" +
                "                </div>\n" +
                "                <div>\n" +
=======
                "            <h1>欢迎访问我的博客</h1>\n" +
                "            <div style=\"margin-top:2em; width: 50%\">\n" +
                "                <div>\n" +
>>>>>>> ff52b43 (期末修改2)
                "                    <div style=\"float:left;padding-right:3em\"><a href=\"./login\">登录后台</a></div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <br><hr width='80%'>\n" +
                "          "  + sb.toString() +
                "        </center>\n" +
                "    </body>\n" +
                "</html>";

        response.setContentType("text/html; Charset=utf8");
        try(Writer writer = response.getWriter()) {
            writer.write(page);
        }
    }

}
