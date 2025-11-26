package school.Hogwarts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import school.Hogwarts.model.StudentAge;
import school.Hogwarts.model.StudentName;
import school.Hogwarts.service.StudentService;

import java.util.List;

@RestController
public class StudentNameController {

    private final StudentService studentService;

    public StudentNameController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students-name")
    public List<StudentName> getCountStudent(){
        return studentService.getCountStudents();
    }
}
