package com.code.dao;


import com.code.cache.Employee;

public interface EmployeeCacheMapper {
    Employee getEmpById(Integer id);
}
