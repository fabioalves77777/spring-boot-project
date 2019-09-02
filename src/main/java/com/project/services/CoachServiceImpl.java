package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Coach;
import com.project.model.CoachModel;
import com.project.repository.CoachRepository;

@Service
public class CoachServiceImpl extends CrudServiceImpl<Coach, CoachModel> 
{	
	@Autowired
	private CoachRepository coachRepository;
	
	public List<CoachModel> findAllCoachs()
	{
		return findAll();
	}
	
	public CoachModel findCoach(Long id) 
	{
		return findOne(id);
	}
	
	public Coach findCoachByName(String name) 
	{
		return coachRepository.findByName(name);
	}
	
	public CoachModel createCoach(CoachModel model) 
	{
		return create(model);
	}
	
	public CoachModel updateCoach(CoachModel model) 
	{
		return update(model);
	}
	
	public void deleteCoach(Long id) 
	{
		delete(id);
	}
	
}
