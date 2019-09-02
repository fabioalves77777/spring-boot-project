package com.project.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.entity.Role;
import com.project.entity.User;
import com.project.repository.RoleRepository;
import com.project.repository.UserRepository;

@Service
public class UserServiceImpl
{
	private UserRepository userRepository;
    private RoleRepository roleRepository;	
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) 
	{
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
	
	public Optional<User> findByUsername(String username) 
	{
        return userRepository.findByUsername(username);
    }
	
	public Boolean existsByUsername(String username) 
	{
		return userRepository.existsByUsername(username);
	}
	
	public User saveUser(User user) 
	{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByName("ROLE_USER"))));
        return userRepository.save(user);
    }
	
}
