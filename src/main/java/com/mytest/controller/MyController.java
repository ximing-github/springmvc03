package com.mytest.controller;


import com.mytest.beans.Student;

import com.mytest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


/**
 * ClassName:MyController
 * Package: com.mytest.controller
 * Description:
 *
 * @Date: 2021/5/12 20:56
 * @Author: 惜名
 */
@Controller
public class MyController {
   @Autowired
    private  StudentService studentService;

   @RequestMapping(value = "/some.do" ,method = RequestMethod.GET)
    public  ModelAndView test(){
        ModelAndView mv = new ModelAndView();
        Student student = new Student();
        student.setName("张三");
        int i=10/0;
        student.setAge("11");
        studentService.addStudent(student);
        //mv.setViewName("hello");
      // mv.setViewName("forward:/jsp/hello.jsp");
       mv.setViewName("redirect:/jsp/hello.jsp");
        return mv;
    }

}
