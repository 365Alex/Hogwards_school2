package school.Hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import school.Hogwarts.model.*;
import school.Hogwarts.repository.StudentRepository;


import java.util.Collection;
import java.util.List;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);


    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student){

        logger.info("Was invoked method for addStudent");
       return studentRepository.save(student);

    }


    public Student findStudent(long id){
        logger.info("Was invoked method for findStudent");

        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for editStudent");
        if (!studentRepository.existsById(student.getId())) {
            return null;
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(long id){
        logger.info("Was invoked method for deleteStudent");

        studentRepository.deleteById(id);
    }
    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method for findByAge");
        return studentRepository.findByAge(age);
    }
    public Collection<Student> getAllStudents() {
        logger.info("Was invoked method for getAllStudents");
        return studentRepository.findAll();
    }
    public Collection<Student> findByAgeBetween(int minAge, int maxAge) {
        logger.info("Was invoked method for findByAgeBetween");
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }

    public Student addStudentToFaculty(Student student, Faculty faculty) {
        logger.info("Was invoked method for addStudentToFaculty");
        student.setFaculty(faculty);
        return studentRepository.save(student);
    }

    public List<StudentName> getCountStudents(){
        logger.info("Was invoked method for getCountStudents");
        return studentRepository.getCountStudents();
    }

    public List<StudentAge> getAgeAvg(){
        logger.info("Was invoked method for getAgeAvg");
        return studentRepository.getAgeAvg();
    }

    public List<StudentOffset> getStudentOffset(){
        logger.info("Was invoked method for getStudentOffset");
        return studentRepository.studentOffset();
    }


}
