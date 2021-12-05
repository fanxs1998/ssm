package com.code.controller;

import com.code.bean.Emp;
import com.code.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class EmpController {

    @Autowired
    EmpService empService;

    @RequestMapping("/getEmps")
    public ModelAndView getEmps(){

        List<Emp> emps = empService.getEmpList();
        ModelAndView mav = new ModelAndView("empList");
        mav.addObject("allEmps",emps);
        return mav;
    }
}
