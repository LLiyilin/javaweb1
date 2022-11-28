package cn.edu.swu.db;

import java.sql.SQLException;

//删除
public class DBTest {
    public static void main(String[] args) throws SQLException {
        DBEngine engine =DBEngine.getInstance();
        String sql = "delete from book where id = 1";
        engine.execute(sql);
    }
}
