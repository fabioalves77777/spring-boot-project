package com.project.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Team extends BaseEntity
{
	public Team() {}
	
	public Team(String name) 
	{
		this.name = name;
	}
	
	private String name;
	
	@OneToOne(fetch = FetchType.EAGER)
	private League league;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Division division;
	
	@OneToMany(fetch = FetchType.EAGER)
    private Set<Player> players;
	
	@OneToOne(fetch = FetchType.EAGER)
    private Coach coach;

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public League getLeague() 
	{
		return league;
	}

	public void setLeague(League league) 
	{
		this.league = league;
	}

	public Set<Player> getPlayers() 
	{
		return players;
	}

	public void setPlayers(Set<Player> players) 
	{
		this.players = players;
	}

	public Coach getCoach() 
	{
		return coach;
	}

	public void setCoach(Coach coach) 
	{
		this.coach = coach;
	}

	public Division getDivision() 
	{
		return division;
	}

	public void setDivision(Division division) 
	{
		this.division = division;
	}
	
	public String toString() 
    {		
		return this.name;		
    }
	
}
