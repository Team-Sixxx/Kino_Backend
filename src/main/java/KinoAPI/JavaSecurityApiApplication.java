package KinoAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@SpringBootApplication( exclude = {SecurityAutoConfiguration.class} )
@SpringBootApplication
//@EntityScan(basePackages = "KinoAPI.models")
public class JavaSecurityApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSecurityApiApplication.class, args);
    }

}
