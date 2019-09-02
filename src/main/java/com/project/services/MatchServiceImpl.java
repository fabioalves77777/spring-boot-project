package com.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.entity.Match;
import com.project.model.MatchModel;

@Service
public class MatchServiceImpl extends CrudServiceImpl<Match, MatchModel> 
{
	public List<MatchModel> findAllMatchs()
	{
		return findAll();
	}
	
	public MatchModel findMatch(Long id) 
	{
		return findOne(id);
	}
	
	public MatchModel createMatch(MatchModel model) 
	{
		return create(model);
	}
	
	public MatchModel updateMatch(MatchModel model) 
	{
		return update(model);
	}
	
	public void deleteMatch(Long id) 
	{
		delete(id);
	}
}
