package com.boutique.Boutique.Angular.Spring;

import com.boutique.Boutique.Angular.Spring.model.ERole;
import com.boutique.Boutique.Angular.Spring.model.Role;
import com.boutique.Boutique.Angular.Spring.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
		roleRepository.save(new Role(ERole.ROLE_MODERATOR));
		roleRepository.save(new Role(ERole.ROLE_CLIENT));

	}*/

}
