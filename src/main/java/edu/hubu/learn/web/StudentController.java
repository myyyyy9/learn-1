package edu.hubu.learn.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import edu.hubu.learn.entity.Student;
import edu.hubu.learn.service.StudentService;


@Controller
@RequestMapping("/student")
public class StudentController {

   

    @Autowired
    private StudentService studentService;

    @RequestMapping("/{id}")
    public ModelAndView student(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        Student student = studentService.getStudent(id);
        mav.addObject("student", student);
        mav.setViewName("student");
        return mav;
    }

    @RequestMapping("/list")
    public ModelAndView student() {
        ModelAndView mav = new ModelAndView();
        List<Student> students = studentService.getStudents();
        mav.addObject("students", students);
        mav.setViewName("students");
        return mav;
    }
    @RequestMapping("/add")
    public ModelAndView addstudent() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("student_add");
        return mav;
    }

    @RequestMapping("/do_add")
    public ModelAndView doAddStudent(Student student) {
        studentService.addUser(student);
        ModelAndView mav = new ModelAndView("redirect:/student/list");
        return mav;
    }
    
}