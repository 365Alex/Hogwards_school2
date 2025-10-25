package school.Hogwarts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.Hogwarts.model.Faculty;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByColor(String color);

}
