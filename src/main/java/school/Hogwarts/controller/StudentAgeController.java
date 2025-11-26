package school.Hogwarts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import school.Hogwarts.model.StudentAge;
import school.Hogwarts.service.StudentService;

import java.util.List;

@RestController
public class StudentAgeController {
    private final StudentService studentService;

    public StudentAgeController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students-age")
    public List<StudentAge> getAgeAvg(){
        return studentService.getAgeAvg();
    }
}
