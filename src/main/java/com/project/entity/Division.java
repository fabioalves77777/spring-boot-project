package com.project.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Division extends BaseEntity
{
	public Division() { }

	public Division(String name) 
	{
		this.name = name;
	}
	
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "divisions", fetch = FetchType.EAGER)
    private Set<League> leagues;
    
    @JsonIgnore
    @OneToMany(mappedBy = "division", fetch = FetchType.EAGER)
    private Set<Team> teams;
    
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

	public Set<League> getLeagues() 
	{
		return leagues;
	}

	public void setLeagues(Set<League> leagues) 
	{
		this.leagues = leagues;
	}
	
	public Set<Team> getTeams() 
	{
		return teams;
	}

	public void setTeams(Set<Team> teams) 
	{
		this.teams = teams;
	}

	public String toString() 
    {		
		return this.name;		
    }
	
}
