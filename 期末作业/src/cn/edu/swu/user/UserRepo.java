package cn.edu.swu.user;

import cn.edu.swu.bolg.dao.MysqlInstall;
import cn.edu.swu.bolg.dao.Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepo {

    private static UserRepo instance = new UserRepo();

    private UserRepo() {
    }

    public static UserRepo getInstance() {
        return instance;
    }

    public void save(User user) throws SQLException {
        String template =
                "INSERT INTO `user`( name, user, password) " +
                "VALUES (\"%s\", \"%s\", MD5(\"%s\"))";
        String sql = String.format(template, user.getName(), user.getUser(), user.getPassword());
        MysqlInstall.getInstance().execute(sql);
    }

    public User auth(String user, String password) throws SQLException {
        String template = "SELECT * FROM `user` WHERE `user` = \"%s\" AND `password` = MD5(\"%s\")";
        List<User> users = MysqlInstall.getInstance().query(
            String.format(template, user, password), new Jdbc<User>() {
            @Override
            public User visit(ResultSet rs) throws SQLException {
                return UserRepo.getUserByResultSet(rs);
            }
        });
        return users.size() == 0 ? null : users.get(0);
    }

    public void delete(User user) throws SQLException {
        String template = "DELETE FROM `user` WHERE `id` = \"%s\"";
        MysqlInstall.getInstance().execute(String.format(template, user.getId()));
    }

    public List<User> getAll() throws SQLException {
        String sql = "SELECT `id`, `name`, `user`, `password` FROM `user`";
        return MysqlInstall.getInstance().query(sql, new Jdbc<User>() {
            @Override
            public User visit(ResultSet rs) throws SQLException {
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
}
