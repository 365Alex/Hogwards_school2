package school.Hogwarts.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import school.Hogwarts.model.Faculty;

import school.Hogwarts.model.Student;
import school.Hogwarts.service.FacultyService;


import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest (controllers = FacultyController.class)
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FacultyService facultyService;

    @Test
    public void saveFacultyTest() throws Exception{
        final String name = "545454";
        final String color = "vghcgcghc";
        final long id = 1L;
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyService.addFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/faculty")
                        .content(facultyObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void testGetFacultyInfo() throws Exception{
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", "gwgfaff");
        facultyObject.put("color", "ervcw");

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("gwgfaff");
        faculty.setColor("ervcw");
        when(facultyService.findFaculty(any(Long.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/1")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("gwgfaff"))
                .andExpect(jsonPath("$.color").value("ervcw"));
    }

    @Test
    public void testGetFacultyInfoNotFound() throws Exception {
        final String name = "vyycyc";
        final String color = "hbbkjb";
        final long id = 2L;
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyService.findFaculty(any(Long.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/2")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testEditFaculty() throws Exception {
        final String name = "vyycyc";
        final String color = "hbbkjb";
        final long id = 2L;
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        when(facultyService.editFaculty(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                .put("/faculty")
                .contentType(MediaType.APPLICATION_JSON)
                .content(facultyObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void testDeleteFaculty() throws Exception{


        mockMvc.perform(MockMvcRequestBuilders
                .delete("/faculty/2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindFacultiesWithoutColor() throws Exception {
        final String name = "vyycyc";
        final String color = "hbbkjb";
        final long id = 2L;
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);

        Collection<Faculty> faculties = Arrays.asList(faculty);
        when(facultyService.getAllFaculty()).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].name").value(name))
                .andExpect(jsonPath("$[0].color").value(color));
    }

    @Test
    public void testSearchFaculties() throws Exception {
        final String name = "vyycyc";
        final String color = "hbbkjb";
        final long id = 2L;
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName(name);
        faculty.setColor(color);
        Collection<Faculty> faculties = Arrays.asList(faculty);
        when(facultyService.findByNameOrColor("Gryffindor", "Gryffindor")).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                                .get("/faculty/search?searchQuery=Gryffindor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id));
    }

    @Test
    public void testSearchFacultiesBadRequest() throws Exception {
               mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/search?searchQuery="))
                .andExpect(status().isBadRequest());
    }



}
