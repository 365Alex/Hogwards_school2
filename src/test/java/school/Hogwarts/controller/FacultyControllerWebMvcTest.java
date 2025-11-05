package school.Hogwarts.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import school.Hogwarts.model.Faculty;
import school.Hogwarts.repository.FacultyRepository;
import school.Hogwarts.repository.StudentRepository;
import school.Hogwarts.service.FacultyService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void saveFacultyTest() throws Exception{
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "545454");
        facultyObject.put("color", "8787");

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("545454");
        faculty.setColor("8787");

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/faculty")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$, id").value("id"))
                .andExpect(jsonPath("$, name").value("name"))
                .andExpect(jsonPath("$, color").value("color"));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/faculty/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$, id").value("id"))
                .andExpect(jsonPath("$, name").value("name"))
                .andExpect(jsonPath("$, color").value("color"));
    }


}
