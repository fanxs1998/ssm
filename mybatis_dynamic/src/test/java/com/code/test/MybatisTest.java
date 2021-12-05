package com.code.test;

import com.code.bean.Employee;
import com.code.dao.EmployeeDynamicSqlMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MybatisTest {

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

    /**
     * 动态sql
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2.获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession(true);


        try {

            EmployeeDynamicSqlMapper mapper = openSession.getMapper(EmployeeDynamicSqlMapper.class);

            Employee employee = new Employee(null, null, null, null, null);
//            List<Employee> employees = mapper.getEmpByConditionIf(employee);
//            List<Employee> employees = mapper.getEmpByConditionTrim(employee);
//            List<Employee> employees = mapper.getEmpByConditionChoose(employee);

            List<Employee> employees = mapper.getEmpByConditionForeach(Arrays.asList(1,2,3,4));
            for (Employee e : employees){
                System.out.println(e);
            }

        } finally {
            openSession.close();
        }
    }

    /**
     * 批量插入
     * @throws IOException
     */
    @Test
    public void testBatchSave() throws IOException {
        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2.获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            EmployeeDynamicSqlMapper mapper = openSession.getMapper(EmployeeDynamicSqlMapper.class);

            List<Employee> emps = new ArrayList<>();
            emps.add(new Employee(null, "test01", "0", "test01@qq.com", 1));
            emps.add(new Employee(null, "test02", "1", "test02@qq.com", 1));
            emps.add(new Employee(null, "test03", "0", "test03@qq.com", 1));

            mapper.addEmps(emps);


        } finally {
            openSession.close();
        }
    }
}
