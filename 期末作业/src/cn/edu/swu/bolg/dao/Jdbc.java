package cn.edu.swu.bolg.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Jdbc<T> {
    public T visit(ResultSet rs) throws SQLException;
}
