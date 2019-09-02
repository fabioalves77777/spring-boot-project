package com.project.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Player extends BaseEntity
{  
    public Player() { }
 
    public Player(String name)
    {
        this.name = name;
    }
    
    private String name;
    
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Goal> goals;
    
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Assist> assists;
    
    @OneToOne(fetch = FetchType.EAGER)
    private Position position;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Team team;
 
    public String getName()
    {
        return name;
    }
 
    public void setName(String name)
    {
        this.name = name;
    }

	public Set<Goal> getGoals() 
	{
		return this.goals;
	}

	public void setGoals(Set<Goal> goals) 
	{
		this.goals = goals;
	}

	public Set<Assist> getAssists() 
	{
		return assists;
	}

	public void setAssists(Set<Assist> assists) 
	{
		this.assists = assists;
	}	

	public Position getPosition() 
	{
		return position;
	}

	public void setPosition(Position position) 
	{
		this.position = position;
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
