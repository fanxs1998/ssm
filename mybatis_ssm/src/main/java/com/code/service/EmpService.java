package com.code.service;

import com.code.bean.Emp;

import java.util.List;

public interface EmpService {
    List<Emp> getEmpList();

    void addBatch(Emp e);
}
