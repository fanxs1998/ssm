package com.code.dao;

import com.code.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    // 多条记录封装一个map，Map<Integer,Employee>:key是记录的主键，值是javabean
    // 告诉mybatis封装这个map的时候使用哪个属性作为map的key
    @MapKey("id")
    Map<Integer, Employee> getEmpByLastNameLikeReturnMap(String lastName);

    // 返回一条记录的map， key就是列名
    Map<String,Object> getEmpByReturnMap(Integer id);

    List<Employee> getEmpsByLastNameLike(String lastName);

    Employee getEmpById(Integer id);

    Employee getEmpByIdAndLastName(@Param("id")Integer id, @Param("lastName") String lastName);

    void addEmp(Employee employee);

    void updateEmp(Employee employee);

    void deleteEmp(Integer id);

}
