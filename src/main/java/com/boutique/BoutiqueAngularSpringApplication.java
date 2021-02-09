package com.boutique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoutiqueAngularSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoutiqueAngularSpringApplication.class, args);
	}

	/*@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {

		roleRepository.save(new Role(ERole.ROLE_ADMIN));
		roleRepository.save(new Role(ERole.ROLE_USER));
		roleRepository.save(new Role(ERole.ROLE_CLIENT));

	}
*/
}
