package certus.edu.pe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroAdminRegistryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroAdminRegistryServerApplication.class, args);
	}

}
