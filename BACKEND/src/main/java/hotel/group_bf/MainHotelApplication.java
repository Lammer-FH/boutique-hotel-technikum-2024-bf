package hotel.group_bf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MainHotelApplication {
	public static void main(String[] args) {
		SpringApplication.run(MainHotelApplication.class, args);
	}
}

