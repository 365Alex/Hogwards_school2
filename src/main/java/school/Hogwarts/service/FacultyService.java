package school.Hogwarts.service;

import org.springframework.stereotype.Service;
import school.Hogwarts.model.Faculty;
import school.Hogwarts.repository.FacultyRepository;


import java.util.Collection;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }

    public Faculty fiendFaculty(long id){

        return facultyRepository.findById(id).get();
    }
    public Faculty editFaculty(Faculty faculty){
        if (!facultyRepository.existsById(faculty.getId())){
            return null;
        }
        return facultyRepository.save(faculty);

    }

    public void deleteFaculty(long id){

        facultyRepository.deleteById(id);
    }
    public Collection<Faculty> findByColor(String color) {

        return facultyRepository.findByColor(color);
    }

    public Collection<Faculty> getAllFaculty(){
        return facultyRepository.findAll();
    }
}
