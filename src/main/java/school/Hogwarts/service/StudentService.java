package school.Hogwarts.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import school.Hogwarts.model.*;
import school.Hogwarts.repository.StudentRepository;


import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student){
       return studentRepository.save(student);

    }


    public Student findStudent(long id){

        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        if (!studentRepository.existsById(student.getId())) {
            return null;
        }
        return studentRepository.save(student);
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
    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Student addStudentToFaculty(Student student, Faculty faculty) {
        student.setFaculty(faculty);
        return studentRepository.save(student);
    }

    public List<StudentName> getCountStudents(){
       return studentRepository.getCountStudents();
    }

    public List<StudentAge> getAgeAvg(){
        return studentRepository.getAgeAvg();
    }

    public List<StudentOffset> getStudentOffset(){
        return studentRepository.studentOffset();
    }


}
