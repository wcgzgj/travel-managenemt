package travelManagement.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("travelManagement")
@MapperScan("travelManagement.mapper")
public class TravelManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelManagementApplication.class, args);
    }

}
