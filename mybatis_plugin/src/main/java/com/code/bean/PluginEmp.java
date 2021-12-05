package com.code.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:fanxs
 * @Date: 2021/12/5 16:36
 * description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PluginEmp {
    private Integer id;
    private String lastName;
    private String gender;
    private String email;
    private EmpStatus empStatus = EmpStatus.LOGIN;
}
