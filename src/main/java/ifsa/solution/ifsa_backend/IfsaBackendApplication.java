package ifsa.solution.ifsa_backend;

import ifsa.solution.ifsa_backend.controller.ClientController;
import ifsa.solution.ifsa_backend.repository.ClientRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication()
@ComponentScan(basePackages = "ifsa.solution.ifsa_backend")
@Configuration
public class IfsaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IfsaBackendApplication.class, args);
	}

}
