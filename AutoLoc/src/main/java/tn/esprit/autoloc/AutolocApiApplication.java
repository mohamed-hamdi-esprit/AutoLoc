package tn.esprit.autoloc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.WebApplicationType;



@SpringBootApplication
@EnableScheduling
public class AutolocApiApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AutolocApiApplication.class);
        app.setWebApplicationType(WebApplicationType.SERVLET);
        app.run(args);
    }
}
