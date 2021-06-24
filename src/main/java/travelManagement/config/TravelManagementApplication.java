package travelManagement.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("travelManagement")
public class TravelManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelManagementApplication.class, args);
    }

}
