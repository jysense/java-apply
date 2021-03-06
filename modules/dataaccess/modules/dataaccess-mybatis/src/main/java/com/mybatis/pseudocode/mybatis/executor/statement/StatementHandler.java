package com.mybatis.pseudocode.mybatis.executor.statement;

import com.mybatis.pseudocode.mybatis.cursor.Cursor;
import com.mybatis.pseudocode.mybatis.executor.parameter.ParameterHandler;
import com.mybatis.pseudocode.mybatis.mapping.BoundSql;
import com.mybatis.pseudocode.mybatis.session.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

//封装了JDBC Statement操作，负责对JDBC statement 的操作，如设置参数、将Statement结果集转换成List集合。
public abstract interface StatementHandler
{
    public abstract Statement prepare(Connection connection, Integer paramInt) throws SQLException;

    //sql参数设置
    public abstract void parameterize(Statement statement) throws SQLException;

    public abstract void batch(Statement statement) throws SQLException;

    public abstract int update(Statement statement) throws SQLException;

    public abstract <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException;

    public abstract <E> Cursor<E> queryCursor(Statement statement) throws SQLException;

    public abstract BoundSql getBoundSql();

    public abstract ParameterHandler getParameterHandler();
}
