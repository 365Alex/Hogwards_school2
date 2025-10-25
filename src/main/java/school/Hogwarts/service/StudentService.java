package school.Hogwarts.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import school.Hogwarts.model.Student;
import school.Hogwarts.repository.StudentRepository;


import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PutMapping
    public Student addStudent(Student student){
       return studentRepository.save(student);

    }

    @GetMapping
    public Student fiendStudent(long id){
        return studentRepository.getReferenceById(id);
    }
    @GetMapping
    public Student editStudent(Student student){
        if (!studentRepository.existsById(student.getId())){
            return null;
        }
        studentRepository.save(student);
        return student;
    }
    @DeleteMapping
    public void deleteStudent(long id){
        studentRepository.deleteById(id);
    }
    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
