package com.code.dao;

import com.code.bean.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpMapper {

    Emp getById(Integer id);

    List<Emp> getEmpList();
}
