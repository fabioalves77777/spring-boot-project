package com.project.model;

import java.util.Set;

public class LeagueModel 
{
	public LeagueModel() { }
	
	public LeagueModel(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }
	
	private Long id;
	private String name;
	private Set<String> divisions;
	
	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public Set<String> getDivisions() 
	{
		return divisions;
	}

	public void setDivisions(Set<String> divisions) 
	{
		this.divisions = divisions;
	}
	
}
