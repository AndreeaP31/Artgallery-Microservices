package artgallery.artwork_management_system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArtworkManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArtworkManagementSystemApplication.class, args);
	}

	@Bean // Adaugă ModelMapper ca bean pentru a putea fi injectat
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
