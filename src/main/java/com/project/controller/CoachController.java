package com.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.CoachModel;
import com.project.services.CoachServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Coach Management System")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class CoachController 
{
	@Autowired
	private CoachServiceImpl coachService;
	
	@GetMapping("/api/coach")
	@ApiOperation(value = "View a list of available coachs", response = List.class)	
	public List<CoachModel> getAllCoachs() 
	{
		return coachService.findAllCoachs();
	}
	
	@GetMapping("/api/coach/{id}")
	@ApiOperation(value = "Get an coach by Id")
	public ResponseEntity<CoachModel> getCoachById(@PathVariable(value = "id") Long id) 
	{
		return ResponseEntity.ok().body(coachService.findCoach(id));
	}
	
	@PostMapping("/api/coach/create")
	@ApiOperation(value = "Add an coach")
	public CoachModel createCoach(@Valid @RequestBody CoachModel model) 
	{
		return coachService.createCoach(model);
	}
	
	@PutMapping("/api/coach/update/{id}")
	@ApiOperation(value = "Update an coach")
	public ResponseEntity<CoachModel> updateCoach(@PathVariable(value = "id") Long id, @Valid @RequestBody CoachModel model) 
	{
		if (coachService.findCoach(id) == null) 
			return new ResponseEntity<CoachModel>(HttpStatus.NOT_FOUND); 
		return ResponseEntity.ok(coachService.updateCoach(model));	
	}
	
	@DeleteMapping("/api/coach/delete/{id}")
	@ApiOperation(value = "Delete an coach")
	public Map<String, Boolean> deleteCoach(@PathVariable(value = "id") Long id)
	{
		coachService.deleteCoach(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
