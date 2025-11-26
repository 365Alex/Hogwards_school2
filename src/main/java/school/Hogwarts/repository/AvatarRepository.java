package school.Hogwarts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.Hogwarts.model.Avatar;


import java.util.Optional;

@Repository
public interface AvatarRepository  extends JpaRepository<Avatar, Long> {

    Page<Avatar> findAll(Pageable pageable);
    Optional<Avatar> findByStudentId(Long studentId);
}
