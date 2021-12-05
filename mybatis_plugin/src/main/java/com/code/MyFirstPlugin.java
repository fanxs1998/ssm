package com.code;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * 完成插件签名：
 *      告诉Mybatis当前插件用来拦截哪个对象的哪个方法
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)})
public class MyFirstPlugin implements Interceptor {
    /**
     * 拦截目标对象的目标方法的执行
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstPlugin...intercept:拦截的目标方法" + invocation.getMethod());

        //执行目标方法
        Object proceed = invocation.proceed();

        //返回执行后的返回值
        return proceed;
    }

    /**
     * 包装目标对象，为目标对象创建代理对象
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        System.out.println("MyFirstPlugin...plugin:mybatis将要包装的对象" + target);

        //借助Plugin类的wrap方法来使用当前Interceptor包装我们的目标对象
        Object wrap = Plugin.wrap(target, this);
        //返回当前target创建的动态代理
        return wrap;
    }

    /**
     * 将插件注册时的property属性设置进来
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的信息："+ properties);
    }
}
