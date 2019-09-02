package com.project.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Assist extends BaseEntity
{
	public Assist() {}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    private Match match;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    private Player player;

	public Match getMatch() 
	{
		return match;
	}

	public void setMatch(Match match) 
	{
		this.match = match;
	}
	
	public Player getPlayer() 
	{
		return player;
	}

	public void setPlayer(Player player) 
	{
		this.player = player;
	}
	
}
