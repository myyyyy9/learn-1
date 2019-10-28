package edu.hubu.learn.web;

import java.util.List;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import edu.hubu.learn.entity.Student;
import edu.hubu.learn.service.StudentService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
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
        studentService.addStudent(student);
        ModelAndView mav = new ModelAndView("redirect:/student/list");
        return mav;
    }
    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        ModelAndView mav = new ModelAndView("redirect:/student/list");
        return mav;
    }
    @RequestMapping("/modify/{id}")
    public ModelAndView modifyStudent(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("student", studentService.getStudent(id));
        mav.setViewName("student_modify");
        return mav;
    }

    @RequestMapping("/do_modify")
    public ModelAndView doModifyStudent(Student student) {
        
        studentService.modifyStudent(student);
        ModelAndView mav = new ModelAndView("redirect:/student/list");
        return mav;
    }
    @RequestMapping("/search")
    public ModelAndView searchStudent() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("student_search");
        return mav;
    }

    @RequestMapping("/do_search")
    public ModelAndView doSearchStudent(HttpServletRequest httpRequest) {
        ModelAndView mav = new ModelAndView();
        String keyword = httpRequest.getParameter("keyword");
        List<Student> students = studentService.searchStudents(keyword);
        mav.addObject("students", students);
        mav.setViewName("students");
        return mav;
    }
    @RequestMapping("/add_avatar/{id}")
    public ModelAndView addStudentAvatar(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("student", studentService.getStudent(id));
        mav.setViewName("student_add_avatar");
        return mav;
    }

    @RequestMapping("/do_add_avatar/{id}")
    public ModelAndView doAddStudentAvatar(@RequestParam("avatar") MultipartFile file, @PathVariable Long id) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath = ResourceUtils.getURL("classpath:").getPath() + "../../../resources/main/static/";
            File dest = new File(filePath + fileName);
            log.info(dest.getAbsolutePath());
            file.transferTo(dest);
            Student student = studentService.getStudent(id);
            student.setAvatar(fileName);
            studentService.modifyStudent(student);
        } catch (Exception e) {
            log.error("upload avatar error", e.getMessage());
        }
        return new ModelAndView("redirect:/student/list");
    }
}