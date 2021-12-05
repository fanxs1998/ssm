package com.code.test;

import com.code.bean.EmpStatus;
import com.code.bean.PluginEmp;
import com.code.dao.PluginEmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
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
     * 在四大对象创建的时候
     * 1.每个创建的对象不是直接返回的，而是
     *      interceptorChain.pluginAll(parameterHandler)
     * 2.获取到所有的Interceptor（拦截器）（插件需要实现的接口）
     *  调用interceptor.plugin(target);返回target包装后的对象
     */
    @Test
    public void test() throws IOException {
        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2.获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession(true);


        try {
            // 3.获取接口的实现类对象
            PluginEmpMapper mapper = openSession.getMapper(PluginEmpMapper.class);

            System.out.println(mapper.getClass());

            List<PluginEmp> list = mapper.listPage();
        } finally {
            openSession.close();
        }
    }


    /**
     * 测试批量操作
     */
    @Test
    public void testBatch() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //可以执行批量操作的sqlSession
        SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            PluginEmpMapper mapper = openSession.getMapper(PluginEmpMapper.class);
            for(int i = 0; i < 10000; i++) {
                PluginEmp dzq = new PluginEmp(null, "dzq", "1", "dzq@123.com", EmpStatus.LOGIN);
                mapper.addEmp(dzq);
            }
            openSession.commit();
        } finally {
            openSession.close();
        }

    }

    /**
     * Oracle分页：
     *      借助rownum：行号，子查询
     *      不能直接查rownum>1 并且rownum<5,查询后行号会发生改变
     *      而是应该借助子查询先查出所有rownum<5的结果，然后在这些结果中查rownum>1
     *
     *存储过程包装分页逻辑
     *
     */
    public void testProcedure() {

    }

    /**
     * mybatis在处理枚举对象的时候默认保存的是枚举对象的名字
     */
    @Test
    public void testEnumUse() {
        EmpStatus status = EmpStatus.LOGIN;
        System.out.println("枚举的索引：" + status.ordinal());
        System.out.println("枚举的名字：" + status.name());
    }

    /**
     * 自定义类型处理器
     * @throws IOException
     */
    @Test
    public void testEnum() throws IOException {
        // 1.获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        // 2.获取SqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession(true);


        try {
            // 3.获取接口的实现类对象
            PluginEmpMapper mapper = openSession.getMapper(PluginEmpMapper.class);

            PluginEmp dzq = new PluginEmp(null, "dzq", "1", "dzq@123.com",EmpStatus.LOGIN);
            mapper.addEmp(dzq);
            openSession.commit();
        } finally {
            openSession.close();
        }
    }

}
