package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Coach extends BaseEntity 
{
	public Coach() {}
	
	public Coach(String name)
    {
        this.name = name;
    }
    
    private String name; 
    
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    private Team team;
	
    public String getName()
    {
        return name;
    }
 
    public void setName(String name)
    {
        this.name = name;
    }

	public Team getTeam() 
	{
		return team;
	}

	public void setTeam(Team team) 
	{
		this.team = team;
	}
	
	public String toString() 
    {		
		return this.name;		
    }
    
}
