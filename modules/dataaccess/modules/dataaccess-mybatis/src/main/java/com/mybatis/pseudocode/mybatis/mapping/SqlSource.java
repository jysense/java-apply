package com.mybatis.pseudocode.mybatis.mapping;

//负责根据用户传递的parameterObject，动态地生成SQL语句，将信息封装到BoundSql对象中，并返回
public abstract interface SqlSource
{
    public abstract BoundSql getBoundSql(Object paramObject);
}
