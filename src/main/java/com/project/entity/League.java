package com.project.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class League extends BaseEntity
{
	public League() { }
	
	public League(String name) 
	{
		this.name = name;
	}
	
	private String name;
	@ManyToMany(fetch = FetchType.EAGER)
    private Set<Division> divisions;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Match> match;
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Set<Division> getDivisions() 
	{
		return divisions;
	}

	public void setDivisions(Set<Division> divisions) 
	{
		this.divisions = divisions;
	}
	
	public Set<Match> getMatch() 
	{
		return match;
	}

	public void setMatch(Set<Match> match) 
	{
		this.match = match;
	}

	public String toString() 
    {		
		return this.name;		
    }
	
}
