package com.project.application;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.entity.Division;
import com.project.entity.Position;
import com.project.entity.Role;
import com.project.entity.User;
import com.project.repository.DivisionRepository;
import com.project.repository.PositionRepository;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;

@SpringBootApplication
@EntityScan(basePackages = { "com.project.entity" })
@EnableJpaRepositories(basePackages = { "com.project.repository" })
@ComponentScan(basePackages = {"com.project.controller", "com.project.services", "com.project.config" })
public class Application extends SpringBootServletInitializer
{
	public static void main(String[] args) 
	{
        SpringApplication.run(Application.class, args);
    }	
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private DivisionRepository divisionRepository;
	@Autowired
	private PositionRepository positionRepository;
	
	@Bean
	InitializingBean sendDataBase() 
	{
	    return () ->  { 
	    	roleRepository.save(new Role("ROLE_USER")); 
	    	roleRepository.save(new Role("ROLE_ADMIN")); 
	    	userRepository.save(setUser());
	    	divisionRepository.save(new Division("A"));
	    	divisionRepository.save(new Division("B"));
	    	divisionRepository.save(new Division("C"));
	    	divisionRepository.save(new Division("D"));
	    	positionRepository.save(new Position("GOLEIRO"));
	    	positionRepository.save(new Position("ZAGUEIRO"));
	    	positionRepository.save(new Position("LATERAL DIREITO"));
	    	positionRepository.save(new Position("LATERAL ESQUERDO"));
	    	positionRepository.save(new Position("VOLANTE"));
	    	positionRepository.save(new Position("MEIA"));
	    	positionRepository.save(new Position("ATACANTE"));
    	};
    }
	
	User setUser() {
		User user = new User();
		user.setUsername("fabio@teste.com");
		user.setPassword(passwordEncoder.encode("123"));
		Set<Role> roles = new HashSet<>();
		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		roles.add(adminRole);
		Role userRole = roleRepository.findByName("ROLE_USER");
		roles.add(userRole);
		user.setRoles(roles);
        return user;
	}
	
}
