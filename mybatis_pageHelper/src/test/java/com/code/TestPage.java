package com.code;


import com.code.bean.E;
import com.code.bean.Employee;
import com.code.dao.EMapper;
import com.code.dao.EmployeeMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.List;

/**
 * @author:fanxs
 * @Date: 2021/12/2 0:13
 * description:
 */
public class TestPage {

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
            EMapper eMapper = openSession.getMapper(EMapper.class);
            Page<Object> page = PageHelper.startPage(1, 5);
            List<E> list = eMapper.getPageList();
            for( E e : list ) {
                System.out.println(e);
            }
            System.out.println("页码：" + page.getPageNum());
            System.out.println("总记录数：" + page.getTotal());
            System.out.println("每页记录数：" + page.getPageSize());
            System.out.println("总页码：" + page.getPages());
        } finally {
            openSession.close();
        }
    }
}
