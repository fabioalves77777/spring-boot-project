package com.project.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Division;
import com.project.entity.League;
import com.project.model.LeagueModel;
import com.project.repository.DivisionRepository;
import com.project.repository.LeagueRepository;

@Service
public class LeagueServiceImpl extends CrudServiceImpl<League, LeagueModel>
{	
	@Autowired
	private LeagueRepository leagueRepository;
	@Autowired
	private DivisionRepository divisionRepository;
	
	public List<Division> findAllDivisions()
	{
		return divisionRepository.findAll();
	}
	
	public Division findDivisionByName(String name) 
	{
		return divisionRepository.findByName(name);
	}
	
	public List<LeagueModel> findAllLeagues()
	{
		return findAll();
	}
	
	public LeagueModel findLeague(Long id) 
	{
		return findOne(id);
	}
	
	public League findLeagueByName(String name) 
	{
		return leagueRepository.findByName(name);
	}
	
	public League createLeague(LeagueModel model) 
	{
		return save(model, null);
	}
	
	public League updateLeague(LeagueModel model, Long id) 
	{
		return save(model, id);
	}
	
	public void deleteLeague(Long id) 
	{
		delete(id);
	}	
	
	public League save(LeagueModel model, Long id) 
	{				
		League league = new League(model.getName());
		if(id != null) league.setId(id);
		Set<String> strDivisions = model.getDivisions();
		Set<Division> divisions = new HashSet<>();		
		strDivisions.forEach(division -> { divisions.add(findDivisionByName(division)); });
		league.setDivisions(divisions);		
		return leagueRepository.save(league);
	}
}
