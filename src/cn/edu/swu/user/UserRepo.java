package cn.edu.swu.user;

import cn.edu.swu.book.Book;
import cn.edu.swu.db.DBEngine;
import cn.edu.swu.db.RecordVisitor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepo {  //对数据库的管理

    private static UserRepo instance = new UserRepo();

    private UserRepo() {
    }

    public static UserRepo getInstance() {
        return instance;
    }
    //用户存储
    public void save(User user) throws SQLException {//实现save功能变成一个sql语句，insert into user表
        String template =//模板
                "INSERT INTO `user`(id, name, user, password) " +
                        "VALUES (\"%s\", \"%s\", \"%s\", MD5(\"%s\"))";    //字符串引起来,MD5是加密
        String sql = String.format(template, user.getId(), user.getName(), user.getUser(), user.getPassword());
        DBEngine.getInstance().execute(sql);
    }
    public User auth(String user, String password) throws SQLException {
        String template = "SELECT * FROM `user` WHERE `user` = \"%s\" AND `password` = MD5(\"%s\")";
        List<User> users = DBEngine.getInstance().query(
                String.format(template, user, password), new RecordVisitor<User>() {
                    @Override
                    public Book visit(ResultSet rs) throws SQLException {
                        return UserRepo.getUserByResultSet(rs);
                    }
                });
        return users.size() == 0 ? null : users.get(0);
    }

    //用户删除
    public void delete(User user) throws SQLException {
        String template = "DELETE FROM `user` WHERE `id` = \"%s\"";
        DBEngine.getInstance().execute(String.format(template, user.getId()));
    }
    //用户查询
    public List<User> getAll() throws SQLException {//泛型
        String sql = "SELECT `id`, `name`, `user`, `password` FROM `user`";
        return DBEngine.getInstance().query(sql, new RecordVisitor<User>() {
            @Override
            public Book visit(ResultSet rs) throws SQLException {
                return UserRepo.getUserByResultSet(rs);

            }
        });

    }
    private static User getUserByResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setUser(rs.getString("user"));
        user.setPassword(rs.getString("password"));
        return user;
    }

    private static User getUserFromResultset(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setUser(rs.getString("user"));
        user.setPassword(rs.getString("password"));
        return user;
    }
    public User getById() throws SQLException {
        String sql = "SELECT * FROM `user` WHERE `id` = %s";
        List<User> users = DBEngine.getInstance().query(
                String.format(sql,getById()), new RecordVisitor<User>() {
                    @Override
                    public Book visit(ResultSet rs) throws SQLException {
                        return UserRepo.getUserFromResultset(rs);
                    }
                });
        return users.size() == 0 ? null : users.get(0);
    }

    private void insertUser(User user) throws SQLException {
        String template =
                "INSERT INTO `user`(id, name, user, password) " +
        "VALUES (\"%s\", \"%s\", \"%s\", MD5(\"%s\"))";
        String sql = String.format(template, user.getName(), user.getId(), user.getUser(), user.getPassword());
        DBEngine.getInstance().execute(sql);
    }

}


