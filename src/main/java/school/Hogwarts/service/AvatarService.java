package school.Hogwarts.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.Hogwarts.model.Avatar;
import school.Hogwarts.repository.AvatarRepository;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Page<Avatar> getAllAvatars(Pageable pageable) {
        return avatarRepository.findAll(pageable);
    }

    public Avatar findAvatarById(Long id) {
        return avatarRepository.findById(id)
                .orElse(null);
    }

    public Avatar saveAvatar(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    public Avatar findAvatarByStudentId(Long studentId) {
        return avatarRepository.findByStudentId(studentId)
                .orElse(null);
    }
    public void deleteAvatar(Long id) {
        avatarRepository.deleteById(id);
    }
}
