package com.code.dao;

import com.code.bean.Page;
import com.code.bean.PluginEmp;

import java.util.List;

/**
 * @author:fanxs
 * @Date: 2021/12/5 16:36
 * description:
 */
public interface PluginEmpMapper {
    List<PluginEmp> listPage();
    void addEmp(PluginEmp pluginEmp);

    /**
     * oracle存储过程
     */
    void getPageByProcedure(Page page);
}
