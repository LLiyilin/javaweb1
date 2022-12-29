package cn.edu.swu.db;

<<<<<<< HEAD
import cn.edu.swu.book.Book;
=======
import cn.edu.swu.bolg.dao.MysqlInstall;
import cn.edu.swu.bolg.dao.Jdbc;
import cn.edu.swu.bolg.model.po.Book;
>>>>>>> ff52b43 (期末修改2)

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

<<<<<<< HEAD

//删除
/*public class DBTest {
    public static void main(String[] args) throws SQLException {
        DBEngine engine =DBEngine.getInstance();
        String sql = "delete from book where id = 1";
        engine.execute(sql);
    }
}*/
//查
public class DBTest {
    public static void main(String[] args) throws SQLException {
        DBEngine engine = DBEngine.getInstance();
        String sql ="SELECT `id`,`name`,`author`,`price`,`describe` from book";
        List<Book> books = engine.query(sql, new RecordVisitor<Book>() {
            @Override
            public Book visit(ResultSet rs) throws SQLException {
                Book book = new Book();//封装
                book.setId(rs.getLong("id"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setPrice(rs.getFloat("price"));
                book.setDescribe(rs.getString("describe"));
                return book;
            }
        });
        for (Book book : books){
            System.out.println(String.format("%s, %s, %s, %s, %s",
                    book.getId(),book.getName(),book.getAuthor(),book.getPrice(),book.getDescribe()));
        }
    }
}
=======
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
>>>>>>> ff52b43 (期末修改2)
