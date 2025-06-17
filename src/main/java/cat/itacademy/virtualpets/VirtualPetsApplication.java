package cat.itacademy.virtualpets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class VirtualPetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualPetsApplication.class, args);
    }

}
