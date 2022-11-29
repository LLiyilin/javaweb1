package cn.edu.swu.db;

import cn.edu.swu.book.Book;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBEngine {
    //单例模式
    private  static DBEngine instance = new DBEngine();
    private BasicDataSource dataSource = null;
    private DBEngine(){

    }

    public static DBEngine getInstance(){
        return instance;
    }
    //连接池
    private BasicDataSource getDataSource(){
        if(this.dataSource == null){
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName("com.mysql.jdbc.Driver");
            ds.setUrl("jdbc:mysql://localhost:3306/book");
            ds.setUsername("root");
            ds.setPassword("529485");
            ds.setInitialSize(3);//connection pool初始化大小
            ds.setMaxIdle(4);//最多四个保持活跃
            this.dataSource = ds;

        }
        return this.dataSource;
    }
    //增删改，不用返回数据
    public  void execute(String sql) throws SQLException {
        try(Connection connection = this.getDataSource().getConnection()){//数据库的连接
            try(Statement statement = connection.createStatement()){
                statement.execute(sql);

            }
        }
    }

    //查
    public <T> List<T> query(String sql, RecordVisitor<Book> visitor) throws SQLException {
        List<T> result = new ArrayList<>();
        try (Connection connection = this.getDataSource().getConnection()) {
            try(Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    while(resultSet.next()) {
                        T obj = (T) visitor.visit(resultSet);
                        result.add(obj);
                    }
                }
            }
        }
        return result;
    }


}

