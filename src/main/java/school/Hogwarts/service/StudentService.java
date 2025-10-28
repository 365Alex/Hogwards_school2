package school.Hogwarts.service;

import org.springframework.stereotype.Service;
import school.Hogwarts.model.Student;
import school.Hogwarts.repository.StudentRepository;


import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student){
       return studentRepository.save(student);

    }


    public Student fiendStudent(long id){
        return studentRepository.getReferenceById(id);
    }

    public Student editStudent(Student student){
        if (!studentRepository.existsById(student.getId())){
            return null;
        }
        studentRepository.save(student);
        return student;
    }

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
