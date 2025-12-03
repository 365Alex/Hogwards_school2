package school.Hogwarts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import school.Hogwarts.model.StudentAge;
import school.Hogwarts.service.StudentService;

import java.util.List;
import java.util.stream.LongStream;

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

    @GetMapping("/improved-stream-sum")
    public ResponseEntity<Long> calculateImprovedStreamSum(){
//        int sum = Stream.iterate(1, a -> a +1)
//                .limit(1_000_000) .reduce(0, (a, b) -> a + b );
        long sum = LongStream.rangeClosed(1, 1_000_000)
                .parallel()
                .sum();
        return ResponseEntity.ok(sum);
    }
}
