package cn.edu.swu.book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public static Connection getConnnection() throws SQLException {
        //加载数据库驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
        //获得connection对象
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/book","root","529485");
        return  connection;//得到一个数据库链接
    }
    //传一本书进来，写进数据库
    public static void SaveBook(Book book) throws SQLException{
        //Book对象转成sql语句，把数据写入数据库
        String template = "INSERT INTO `book`(`name`, `author`, `price`, `describe`) " +
                "VALUES ('%s', '%s', %s, '%s')";//数据库的字段和名字加``以避免被当做关键字
        //获得链接对象
        try(Connection connection = getConnnection()) {
            try(Statement statement = connection.createStatement()){
                String sql = String.format(template,book.getName(),book.getAuthor(),book.getPrice(),book.getDescribe());
                System.out.println(sql);
                statement.execute(sql);
            }
        }
    }

    //获取图书信息
    public static  List<Book> getAllBook() throws SQLException {
        String sql = "SELECT * FROM book";
        try (Connection connection =getConnnection()){//创建链接对象
            try(Statement statement = connection.createStatement()){
                try(ResultSet resultSet = statement.executeQuery(sql)){//查询数据库保存在数据集
                    List<Book> books = new ArrayList<>();//接收数据
                    while(resultSet.next()){//存在下一条
                        long id = resultSet.getLong("id");
                        String name = resultSet.getString("name");
                        String author = resultSet.getString("author");
                        float price = resultSet.getFloat("price");
                        String describe = resultSet.getString("describe");

                        Book book = new Book();//封装
                        book.setName(name);
                        book.setAuthor(author);
                        book.setPrice(Float.valueOf(price));
                        book.setDescribe(describe);

                        books.add(book);
                    }
                    return books;
                }
            }

        }

    }

}
