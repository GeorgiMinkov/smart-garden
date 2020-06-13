package bg.unisofia.fmi.robotcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MsSmartGardenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSmartGardenApplication.class, args);
	}

}
