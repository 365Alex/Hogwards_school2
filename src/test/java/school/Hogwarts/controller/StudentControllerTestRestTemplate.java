package school.Hogwarts.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import school.Hogwarts.model.Student;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void  contextLoads() throws Exception{
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudent() throws Exception{
        Assertions.assertThat(this.testRestTemplate.
                        getForObject("http://localhost:" + port + "/students", String.class)).
                isNotNull();
    }

    @Test
    public void testPostFaculty() throws Exception{
        Student student = new Student();
        student.setName("Hdvdd");
        student.setAge(21);
        Assertions.assertThat(this.testRestTemplate.
                        postForObject("http://localhost:" + port + "/faculty",student, String.class)).
                isNotEmpty();
    }
}
