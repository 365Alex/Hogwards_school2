package school.Hogwarts.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import school.Hogwarts.model.Student;
import school.Hogwarts.service.StudentService;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentController.class)
public class StudentControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Test
    public void saveStudentTest() throws Exception {
        final String name = "Ronald";
        final int age = 20;
        final long id = 1L;
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student students = new Student();
        students.setId(id);
        students.setName(name);
        students.setAge(age);

        when(studentService.addStudent(any(Student.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/students")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

    }

    @Test
    public void testGetStudentInfo() throws Exception{
        final String name = "Ronald";
        final int age = 20;
        final long id = 1L;
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student students = new Student();
        students.setId(id);
        students.setName(name);
        students.setAge(age);

        when(studentService.findStudent(any(Long.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/1")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }
    @Test
    public void testGetStudentInfoNotFound() throws Exception {
        final String name = "Ronald";
        final int age = 20;
        final long id = 1L;
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("age", age);

        Student students = new Student();
        students.setId(id);
        students.setName(name);
        students.setAge(age);
        when(studentService.findStudent(any(Long.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/2")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testEditStudent() throws Exception {
        final String name = "Ronald";
        final int age = 20;
        final long id = 1L;
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student students = new Student();
        students.setId(id);
        students.setName(name);
        students.setAge(age);

        when(studentService.editStudent(any(Student.class))).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentObject.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void testDeleteStudent() throws Exception{
        final String name = "Ronald";
        final int age = 20;
        final long id = 1L;
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student students = new Student();
        students.setId(id);
        students.setName(name);
        students.setAge(age);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/students/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindStudentsWithAge() throws Exception {
        final String name = "Ronald";
        final int age = 20;
        final long id = 1L;
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);
        studentObject.put("age", age);

        Student students = new Student();
        students.setId(id);
        students.setName(name);
        students.setAge(age);

        Collection<Student> student = Arrays.asList(students);
        when(studentService.findByAge(age)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students?age=20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(id))
                .andExpect(jsonPath("$[0].age").value(age));
    }








}
