package com.code.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePlus {
    private Integer id;
    private String lastName;
    private String gender;
    private String email;
    private Department dept;
}
