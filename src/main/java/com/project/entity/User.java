package com.project.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class User extends BaseEntity
{
	public User() { }
	
	public User(String username, String password) 
	{
		this.username = username;
		this.password = password;
	}
	
	private String username;
	private String password; 
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
	
    public String getUsername() 
	{
		return username;
	}
	
	public void setUsername(String name) 
	{
		this.username = name;
	}
    
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
		
	public Set<Role> getRoles() 
	{
		return roles;
	}
	
	public void setRoles(Set<Role> roles) 
	{
		this.roles = roles;
	}   
	
}
