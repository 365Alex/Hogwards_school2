package school.Hogwarts.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.Hogwarts.model.Avatar;
import school.Hogwarts.service.AvatarService;


@RestController
@RequestMapping("/avatars")
public class AvatarTController {

    private final AvatarService avatarService;

    public AvatarTController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping
    public ResponseEntity<Page<Avatar>> getAllAvatars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Avatar> avatars = avatarService.getAllAvatars(pageable);

        return ResponseEntity.ok(avatars);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Avatar> getAvatarByStudentId(@PathVariable Long studentId) {
        Avatar avatar = avatarService.findAvatarByStudentId(studentId);
        if (avatar == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(avatar);
    }

    @PostMapping
    public ResponseEntity<Avatar> createAvatar(@RequestBody Avatar avatar) {
        Avatar savedAvatar = avatarService.saveAvatar(avatar);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAvatar);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatarById(id);
        if (avatar == null) {
            return ResponseEntity.notFound().build();
        }
        avatarService.deleteAvatar(id);
        return ResponseEntity.noContent().build();
    }
}
