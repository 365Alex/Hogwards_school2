package school.Hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import school.Hogwarts.model.*;
import school.Hogwarts.repository.StudentRepository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


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

    public List<String> getStudentNamesStartingWithA() {
        logger.info("Was invoked method for getStudentNamesStartingWithA");

        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name != null && !name.isEmpty())
                .filter(name -> name.toUpperCase().startsWith("A"))
                .map(String::toUpperCase)
                .sorted()
                .distinct()
                .collect(Collectors.toList());
    }

    public Double getAverageAgeOfAllStudents(){
        logger.info("Was invoked method for getAverageAgeOfAllStudents");
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }

    public void printStudentsInParallel(){
        List<Student> students = new ArrayList<>(studentRepository.findAll());
        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

            Thread thread1 = new Thread(() -> {
                System.out.println(students.get(2).getName());
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Поток прерван");
                }
                System.out.println(students.get(3).getName());
            });
            thread1.start();

            Thread thread2 = new Thread(() ->{
                System.out.println(students.get(4).getName());
                System.out.println(students.get(5));
            });
            thread2.start();
    }

    private synchronized void printStudentName(String threadName, String studentName, int delay){

                System.out.println(threadName + " " + studentName);
    }

    public void printStudentsSynchronized() {
        List<Student> students = new ArrayList<>(studentRepository.findAll());

        printStudentName("Основной поток: ", students.get(1).getName(), 80);
        printStudentName("Основной поток: ", students.get(2).getName(), 100);

        Thread threadOne = new Thread(() -> {
            printStudentName("Параллельный поток 1", students.get(3).getName(), 150);
            printStudentName("Параллельный поток 1", students.get(4).getName(), 75);
        });
        threadOne.start();

        Thread threadTwo = new Thread(() -> {
            printStudentName("Параллельный поток 2", students.get(5).getName(), 120);
            printStudentName("Параллельный поток 2", students.get(6).getName(), 110);
        });

        threadTwo.start();

    }

}
