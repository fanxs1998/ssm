package com.code.test;

import com.code.bean.DepartmentPlus;
import com.code.bean.EmployeePlus;
import com.code.dao.DepartmentMapper;
import com.code.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class MybatisTestPlus {
    /**
     * 提供获取sqlSessionFactory对象的公共方法
     * @return
     * @throws IOException
     */
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return  new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test01() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession(true);


        try {
            EmployeeMapperPlus mapperPlus = openSession.getMapper(EmployeeMapperPlus.class);

/*            Employee employee = mapperPlus.getEmpById(1);
            System.out.println(employee);

            //关联查询
            EmployeePlus employeePlus = mapperPlus.getEmpAndDept(1);
            System.out.println(employeePlus);*/

            //分布查询
            EmployeePlus e = mapperPlus.getEmpByIdStep(1);
            System.out.println(e.getEmail());
            System.out.println(e);


        } finally {
            openSession.close();
        }
    }

    @Test
    public void test02() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);


            //关联查询 一对多
//            DepartmentPlus departmentPlus = mapper.getDeptByIdPlus(1);
//            System.out.println(departmentPlus);

            // 分步查询
            DepartmentPlus deptByIdStep = mapper.getDeptByIdStep(1);
            System.out.println(deptByIdStep);
            System.out.println(deptByIdStep.getEmployees());


        } finally {
            openSession.close();
        }
    }
}
