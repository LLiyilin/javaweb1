package cn.edu.swu.db;

import cn.edu.swu.bolg.dao.MysqlInstall;
import cn.edu.swu.bolg.dao.Jdbc;
import cn.edu.swu.bolg.model.po.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBTest {

    public static void main(String[] args) throws SQLException {
//        MysqlInstall engine = MysqlInstall.getInstance();
//        String sql = "SELECT `id`, `name`, `author`, `price`, `describe` from book";
//        List<Book> books = engine.query(sql, new Jdbc<Book>() {
//            @Override
//            public Book visit(ResultSet rs) throws SQLException {
//                Book book = new Book();
//                book.setId(rs.getLong("id"));
//                book.setName(rs.getString("name"));
//                book.setAuthor(rs.getString("author"));
//                book.setPrice(rs.getFloat("price"));
//                book.setDescribe(rs.getString("describe"));
//                return book;
//            }
//        });
//
//        for (Book book : books) {
//            System.out.println(String.format("%s,%s,%s,%s,%s",
//                    book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getDescribe()
//            ));
//        }
    }
}
