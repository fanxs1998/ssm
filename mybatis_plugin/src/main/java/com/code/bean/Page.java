package com.code.bean;

import lombok.Data;

import java.util.List;

/**
 * @author:fanxs
 * @Date: 2021/12/5 19:46
 * description:封装分页查询数据
 */
@Data
public class Page {
    private int start;
    private int end;
    private int count;
    private List<PluginEmp> emps;

}
