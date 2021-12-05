package com.code.dao;

import com.code.bean.Employee;

import java.util.List;

/**
 * @author:fanxs
 * @Date: 2021/12/2 0:06
 * description:
 */
public interface EmployeeMapper {

    /**
     * 分页
     * @return
     */
    List<Employee> getPageList();
}
