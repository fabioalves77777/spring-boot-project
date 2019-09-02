package com.project.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.entity.User;
import com.project.security.jwt.JwtProvider;
import com.project.security.jwt.JwtResponse;
import com.project.security.jwt.ResponseMessage;
import com.project.services.UserServiceImpl;

import io.swagger.annotations.Api;

@RestController
@Api(value = "Authentication Management System")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
public class AuthController
{
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody User user) 
    {   
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
   
      SecurityContextHolder.getContext().setAuthentication(authentication);
   
      String jwt = jwtProvider.generateJwtToken(authentication);
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
   
      return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) 
    {
      if (userService.existsByUsername(user.getUsername())) 
    	  return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"), HttpStatus.BAD_REQUEST);   
      // Creating user's account
      userService.saveUser(user);   
      return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }
    
    
    
}
