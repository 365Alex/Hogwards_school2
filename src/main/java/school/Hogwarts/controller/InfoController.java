package school.Hogwarts.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;

public class InfoController {

    private final Environment environment;

    @Value("${server.port:8080}")
    private String serverPort;

    public InfoController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/port")
    public String getPort() {
        String[] activeProfiles = environment.getActiveProfiles();
        String profile = activeProfiles.length > 0 ? activeProfiles[0] : "default";

        return "Port: " + serverPort + " (Profile: " + profile + ")";
    }
}
