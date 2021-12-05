package com.code.dao;

import com.code.bean.Department;
import com.code.bean.DepartmentPlus;

public interface DepartmentMapper {

    Department getDeptById(Integer id);

    //一对多
    DepartmentPlus getDeptByIdPlus(Integer id);

    //分步查询
    DepartmentPlus getDeptByIdStep(Integer id);
}
