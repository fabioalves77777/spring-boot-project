package com.project.model;

import java.util.Set;

import com.project.entity.Player;

public class TeamModel 
{
	public TeamModel() { }
	
	public TeamModel(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
    private Long id;
    private String name;
    private String coach;
    private Set<Player> players;
    private String league;
    private String division;
    private boolean dismiss;
	
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

	public String getCoach() 
	{
		return coach;
	}

	public void setCoach(String coach) 
	{
		this.coach = coach;
	}

	public Set<Player> getPlayers() 
	{
		return players;
	}

	public void setPlayers(Set<Player> players) 
	{
		this.players = players;
	}

	public String getLeague() 
	{
		return league;
	}

	public void setLeague(String league) 
	{
		this.league = league;
	}

	public String getDivision() 
	{
		return division;
	}

	public void setDivision(String division) 
	{
		this.division = division;
	}

	public boolean isDismiss() 
	{
		return dismiss;
	}

	public void setDismiss(boolean dismiss) 
	{
		this.dismiss = dismiss;
	}
	
}
