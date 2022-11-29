package cn.edu.swu.db;

import cn.edu.swu.book.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RecordVisitor<T> {
    public Book visit(ResultSet rs) throws SQLException;
}

