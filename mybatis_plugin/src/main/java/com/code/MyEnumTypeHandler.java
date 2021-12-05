package com.code;

import com.code.bean.EmpStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author:fanxs
 * @Date: 2021/12/6 1:12
 * description:自定义枚举类型处理器
 * 1、实现TypeHandler接口，或者继承BaseTypeHandler
 *
 */
public class MyEnumTypeHandler implements TypeHandler<EmpStatus> {
    /**
     * 定义当前数据如何保存到数据库
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setParameter(PreparedStatement ps, int i, EmpStatus parameter, JdbcType jdbcType) throws SQLException {
        System.out.println("=============要保存的状态码：============="+ parameter.getCode());
        ps.setString(i,parameter.getCode().toString());
    }

    @Override
    public EmpStatus getResult(ResultSet rs, String columnName) throws SQLException {
        //需要根据从数据库拿到的枚举状态码返回一个枚举对象
        int code = rs.getInt(columnName);
        System.out.println("====================从数据库获取的状态码：================"+code);
        //返回枚举对象
        return EmpStatus.getEmpStatusByCode(code);
    }

    @Override
    public EmpStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        //需要根据从数据库拿到的枚举状态码返回一个枚举对象
        int code = rs.getInt(columnIndex);
        System.out.println("====================从数据库获取的状态码：================："+code);
        //返回枚举对象
        return EmpStatus.getEmpStatusByCode(code);
    }

    @Override
    public EmpStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        //需要根据从数据库拿到的枚举状态码返回一个枚举对象
        int code = cs.getInt(columnIndex);
        System.out.println("====================从数据库获取的状态码：================："+code);
        //返回枚举对象
        return EmpStatus.getEmpStatusByCode(code);
    }
}
