package com.code.cache;

import com.code.dao.EmployeeCacheMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CacheTest {
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
     * 测试二级缓存
     * @throws IOException
     */
    @Test
    public void testSecondLevelCache() throws IOException {
        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2.获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession(true);
        SqlSession openSession2 = sqlSessionFactory.openSession(true);//第二个会话

        try {

            EmployeeCacheMapper mapper = openSession.getMapper(EmployeeCacheMapper.class);
            EmployeeCacheMapper mapper2 = openSession2.getMapper(EmployeeCacheMapper.class);

            Employee empById = mapper.getEmpById(1);
            System.out.println(empById);
            openSession.close();

            //第二次查询是从二级缓存中获取，没有发送sql
            Employee empById2 = mapper2.getEmpById(1);
            System.out.println(empById2);

        } finally {

            openSession2.close();
        }
    }
}
