package com.project.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class Role extends BaseEntity
{
	public Role() { }

	public Role(String name) 
	{
		this.name = name;
	}
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> users;
    private String name;    
    
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Set<User> getUsers() 
	{
		return users;
	}

	public void setUsers(Set<User> users) 
	{
		this.users = users;
	}
	
}
