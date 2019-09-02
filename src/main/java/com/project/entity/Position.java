package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Position extends BaseEntity 
{
	public Position() {}
	
	public Position(String name) 
	{
		this.name = name;
	}
	
	@JsonIgnore
	@OneToOne(mappedBy = "position", fetch = FetchType.EAGER)
    private Player player;
    private String name;    
    
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public Player getPlayer() 
	{
		return player;
	}

	public void setPlayer(Player player) 
	{
		this.player = player;
	}

	public String toString() 
    {		
		return this.name;		
    }
	
}
