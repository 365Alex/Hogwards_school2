package school.Hogwarts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import school.Hogwarts.model.StudentOffset;
import school.Hogwarts.service.StudentService;

import java.util.List;

@RestController
public class StudentOffsetController {

    private final StudentService studentService;

    public StudentOffsetController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students-offset")
    public List<StudentOffset> getStudentOffset(){
        return studentService.getStudentOffset();
    }
}
