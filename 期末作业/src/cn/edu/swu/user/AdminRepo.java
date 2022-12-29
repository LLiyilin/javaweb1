package cn.edu.swu.user;

import cn.edu.swu.bolg.dao.MysqlInstall;
import cn.edu.swu.bolg.dao.Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminRepo {

    private static AdminRepo instance = new AdminRepo();

    private AdminRepo() {
    }

    public static AdminRepo getInstance() {
        return instance;
    }

    public void save(Admin admin) throws SQLException {
        String template =
                "INSERT INTO `admin`( name, user, password) " +
                        "VALUES (\"%s\", \"%s\", MD5(\"%s\"))";
        String sql = String.format(template, admin.getName(), admin.getUser(), admin.getPassword());
        MysqlInstall.getInstance().execute(sql);
    }

    public Admin auth(String user, String password) throws SQLException {
        String template = "SELECT * FROM `admin` WHERE `user` = \"%s\" AND `password` = MD5(\"%s\")";
        List<Admin> admins = MysqlInstall.getInstance().query(
                String.format(template, user, password), new Jdbc<Admin>() {
                    @Override
                    public Admin visit(ResultSet rs) throws SQLException {
                        return AdminRepo.getAdminByResultSet(rs);
                    }
                });
        return admins.size() == 0 ? null : admins.get(0);
    }

    private static Admin getAdminByResultSet(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setId(rs.getString("id"));
        admin.setName(rs.getString("name"));
        admin.setUser(rs.getString("user"));
        admin.setPassword(rs.getString("password"));
        return admin;
    }
}

