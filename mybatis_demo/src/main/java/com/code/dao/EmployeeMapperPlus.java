package com.code.dao;

import com.code.bean.Employee;
import com.code.bean.EmployeePlus;

import java.util.List;

public interface EmployeeMapperPlus {

    Employee getEmpById(Integer id);

    EmployeePlus getEmpAndDept(Integer id);

    /**
     * 使用association进行分步查询
     * @param id
     * @return
     */
    EmployeePlus getEmpByIdStep(Integer id);

    /**
     * 使用collection进行分步查询
     */
    List<Employee> getEmpsByDeptId(Integer deptId);
}
