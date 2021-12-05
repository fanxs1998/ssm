package com.code.service.impl;

import com.code.bean.Emp;
import com.code.dao.EmpMapper;
import com.code.service.EmpService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private SqlSession sqlSession;


    @Override
    public List<Emp> getEmpList() {
        return empMapper.getEmpList();
    }

    @Override
    public void addBatch(Emp e) {
//        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
    }
}
