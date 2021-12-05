package com.code.dao;

import com.code.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * 基于注解实现查询
 */
public interface EmployeeMapperAnnotation {
    @Select("select * from tbl_employee where id = #{id}")
    public Employee getEmpById(Integer id);
}