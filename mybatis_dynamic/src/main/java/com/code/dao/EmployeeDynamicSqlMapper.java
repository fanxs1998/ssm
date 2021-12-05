package com.code.dao;

import com.code.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeDynamicSqlMapper {

    List<Employee> getEmpByConditionIf(Employee employee);

    List<Employee> getEmpByConditionTrim(Employee employee);

    List<Employee> getEmpByConditionChoose(Employee employee);

    List<Employee> getEmpByConditionForeach(@Param("ids") List<Integer> ids);

    void updateEmp(Employee employee);

    //批量插入
    void addEmps(@Param("emps") List<Employee> emps);
}
