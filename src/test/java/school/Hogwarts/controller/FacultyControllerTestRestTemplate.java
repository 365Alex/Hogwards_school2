package school.Hogwarts.controller;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import school.Hogwarts.model.Faculty;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FacultyControllerTestRestTemplate {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void  contextLoads() throws Exception{
        Assertions.assertThat(facultyController).isNotNull();
    }

    @Test
    public void testGetFaculty() throws Exception{
        Assertions.assertThat(this.testRestTemplate.
                getForObject("http://localhost:" + port + "/faculty", String.class)).
                isNotEmpty();
    }

    @Test
    public void testPostFaculty() throws Exception{
        Faculty faculty = new Faculty();
        faculty.setName("Tegvd");
        faculty.setColor("gray");
        Assertions.assertThat(this.testRestTemplate.
                        postForObject("http://localhost:" + port + "/faculty",faculty, String.class)).
                isNotEmpty();
    }
}
