package school.Hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.Hogwarts.model.Student;
import school.Hogwarts.model.StudentAge;
import school.Hogwarts.model.StudentName;
import school.Hogwarts.model.StudentOffset;


import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAge(int age);
    List<Student> findByAgeBetween(int minAge, int maxAge);

    @Query(value = "select count(name) from student", nativeQuery = true)
    List<StudentName> getCountStudents();

    @Query(value = "select min(age), max(age), avg(age) from student", nativeQuery = true)
    List<StudentAge> getAgeAvg();

    @Query(value = "SELECT * FROM student OFFSET 3", nativeQuery = true)
    List<StudentOffset> studentOffset();

}
