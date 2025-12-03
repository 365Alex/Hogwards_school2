package school.Hogwarts.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import school.Hogwarts.model.Faculty;
import school.Hogwarts.repository.FacultyRepository;


import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private static final Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty){
        logger.debug("Attempting to add new faculty: {}", faculty.getName());
        Faculty savedFaculty = facultyRepository.save(faculty);
        logger.info("Successfully added faculty: {} with ID: {}", savedFaculty.getName(), savedFaculty.getId());
        return savedFaculty;
    }

    public Faculty findFaculty(long id){
        logger.debug("Searching for faculty with ID: {}", id);

        return facultyRepository.findById(id)
                .map(faculty -> {
                    logger.debug("Found faculty: {} with ID: {}", faculty.getName(), faculty.getId());
                    return faculty;
                })
                .orElseGet(() -> {
                    logger.warn("Faculty with ID: {} not found", id);
                    return null;
                });
    }
    public Faculty editFaculty(Faculty faculty){
        logger.debug("Attempting to edit faculty with ID: {}", faculty.getId());

        if (!facultyRepository.existsById(faculty.getId())) {
            logger.warn("Cannot edit faculty: faculty with ID: {} not found", faculty.getId());
            return null;
        }

        Faculty updatedFaculty = facultyRepository.save(faculty);
        logger.info("Successfully updated faculty: {} with ID: {}", updatedFaculty.getName(), updatedFaculty.getId());
        return updatedFaculty;
    }

    public void deleteFaculty(long id){
        logger.debug("Attempting to delete faculty with ID: {}", id);

        if (facultyRepository.existsById(id)) {
            facultyRepository.deleteById(id);
            logger.info("Successfully deleted faculty with ID: {}", id);
        } else {
            logger.warn("Cannot delete faculty: faculty with ID: {} not found", id);
        }
    }
    public Collection<Faculty> findByColor(String color) {
        logger.debug("Searching for faculties with color: {}", color);
        Collection<Faculty> faculties = facultyRepository.findByColor(color);
        logger.debug("Found {} faculties with color: {}", faculties.size(), color);
        return faculties;
    }

    public Collection<Faculty> getAllFaculty(){
        logger.debug("Retrieving all faculties");
        Collection<Faculty> faculties = facultyRepository.findAll();
        logger.debug("Retrieved {} faculties", faculties.size());
        return faculties;

    }
    public Collection<Faculty> findByNameOrColor(String name, String color) {
        logger.debug("Searching for faculties by name: {} or color: {}", name, color);
        Collection<Faculty> faculties = facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
        logger.debug("Found {} faculties matching name: {} or color: {}", faculties.size(), name, color);
        return faculties;

    }

    public String  getLongestFacultyName(){
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .filter(name -> name != null && !name.trim().isEmpty())
                .max(Comparator.comparingInt(String::length))
                .orElse("не найдено");

    }
}
