package com.project.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Match extends BaseEntity
{
	public Match() {}
	
	public Match(int round, League league, Division division, String teamA, String teamB) 
	{
		this.round = round;
		this.league = league;
		this.division = division;
		this.teamA = teamA;
		this.teamB = teamB;
	}
	
	private int round;
	private String teamA;
	private String teamB;
	private String winner;
	private String loser;
	private boolean draw;
	private int goalsTeamA;
	private int goalsTeamB;
	
	@OneToOne(fetch = FetchType.EAGER)
	private League league;
	@OneToOne(fetch = FetchType.EAGER)
	private Division division;	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Goal> goals;
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Assist> assists;
	
	public int getRound() 
	{
		return round;
	}

	public void setRound(int round) 
	{
		this.round = round;
	}
	
	public League getLeague() 
	{
		return league;
	}

	public void setLeague(League league) 
	{
		this.league = league;
	}

	public Division getDivision() 
	{
		return division;
	}

	public void setDivision(Division division) 
	{
		this.division = division;
	}

	public String getTeamA() 
	{
		return teamA;
	}

	public void setTeamA(String teamA) 
	{
		this.teamA = teamA;
	}

	public String getTeamB() 
	{
		return teamB;
	}

	public void setTeamB(String teamB) 
	{
		this.teamB = teamB;
	}

	public String getWinner() 
	{
		return winner;
	}

	public void setWinner(String winner) 
	{
		this.winner = winner;
	}

	public String getLoser() 
	{
		return loser;
	}

	public void setLoser(String loser) 
	{
		this.loser = loser;
	}

	public boolean isDraw() 
	{
		return draw;
	}

	public void setDraw(boolean draw) 
	{
		this.draw = draw;
	}

	public int getGoalsTeamA() 
	{
		return goalsTeamA;
	}

	public void setGoalsTeamA(int goalsTeamA) 
	{
		this.goalsTeamA = goalsTeamA;
	}

	public int getGoalsTeamB() 
	{
		return goalsTeamB;
	}

	public void setGoalsTeamB(int goalsTeamB) 
	{
		this.goalsTeamB = goalsTeamB;
	}

	public Set<Goal> getGoals() 
	{
		return goals;
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
	
}
