package com.code.test;

import com.code.bean.Employee;
import com.code.dao.EmployeeMapper;
import com.code.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

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
     * 入门案例
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 根据配置文件获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession(true);

        try {
            Employee employee = openSession.selectOne("com.code.dao.EmployeeMapper.getEmpById", 1);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    /**
     * 面向mapper接口编程
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {

        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2.获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession(true);


        try {
            // 3.获取接口的实现类对象
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            System.out.println(mapper.getClass());

            Employee employee = mapper.getEmpById(1);

            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    /**
     * 基于注解实现查询
     * @throws IOException
     */
    @Test
    public void test02() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession(true);


        try {
            EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);

            Employee employee = mapper.getEmpById(1);

            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    /**
     * 增删改
     */
    @Test
    public void test03() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 1.获取的SqlSession不会自动提交数据
        SqlSession openSession = sqlSessionFactory.openSession();

        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            // 新增
            Employee dzq = new Employee(null, "dzq", "1", "dzq@123.com");
            mapper.addEmp(dzq);
            System.out.println(dzq.getId());

            // 修改
//            Employee dzq = new Employee(2,"dzq", "1", "dzq@163.com");
//            mapper.updateEmp(dzq);

            // 删除
//            mapper.deleteEmp(2);

            // 2.手动提交数据
            openSession.commit();

        } finally {
            openSession.close();
        }
    }

    /**
     * 多个参数查询
     * @throws IOException
     */
    @Test
    public void test04() throws IOException {

        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession(true);


        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);

            Employee employee = mapper.getEmpByIdAndLastName(1,"fanxs");

            System.out.println(employee);

            // 按照名字模糊查询
            List<Employee> list = mapper.getEmpsByLastNameLike("%f%");

            for (Employee e: list) {
                System.out.println(e);
            }

            // Map<Integer,Employee>
            Map<Integer,Employee> employeesMap = mapper.getEmpByLastNameLikeReturnMap("%f%");
            System.out.println(employeesMap);
        } finally {
            openSession.close();
        }
    }

}
