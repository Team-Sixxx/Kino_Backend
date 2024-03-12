package KinoAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication( exclude = {SecurityAutoConfiguration.class} )
@SpringBootApplication
public class JavaSecurityApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSecurityApiApplication.class, args);
    }

}
