package com.project.model;

public class PlayerModel
{
    public PlayerModel() { }
 
    public PlayerModel(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }
    
    private Long id;
    private String name;
    private int goals;
    private int assists;
    private String position;
 
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
 
    public int getAssists()
    {
        return assists;
    }
 
    public void setAssists(int assists)
    {
        this.assists = assists;
    }
 
    public int getGoals()
    {
        return goals;
    }
 
    public void setGoals(int goals)
    {
        this.goals = goals;
    }

	public String getPosition() 
	{
		return position;
	}

	public void setPosition(String position) 
	{
		this.position = position;
	}

}