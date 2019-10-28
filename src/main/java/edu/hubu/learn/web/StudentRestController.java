package edu.hubu.learn.web;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.RestController;

 import edu.hubu.learn.entity.Result;
 import edu.hubu.learn.entity.Student;
 import edu.hubu.learn.service.StudentService;

 @RestController

 @RequestMapping("/rest/student")
 public class StudentRestController {

     @Autowired
     private StudentService studentService;

     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     public Object getById(@PathVariable("id") long id) {
         return studentService.getStudent(id);
     }

     @RequestMapping(value = "/list", method = RequestMethod.GET)
     public Object getAll() {
         return studentService.getStudents();
     }

     @RequestMapping(method = RequestMethod.POST)
     public Object addStudent(Student student) {
         studentService.addStudent(student);
         return student;
     }

     @RequestMapping(method = RequestMethod.PUT)
     public Object modifyStudent(Student student) {
         studentService.modifyStudent(student);
         return student;
     }

     @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
     public Object deleteStudent(@PathVariable("id") long id) {
         try {
             studentService.deleteStudent(id);
         } catch (Exception e) {
             return new Result(false, e);
         }
         return new Result(true, null);
     }

     @RequestMapping(value = "/search/{key}", method = RequestMethod.GET)
     public Object searchStudent(String key) {
         return studentService.searchStudents(key);
     }
 } 