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

import com.project.entity.Division;
import com.project.entity.League;
import com.project.model.LeagueModel;
import com.project.services.LeagueServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "League Management System")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class LeagueController 
{
	@Autowired
	private LeagueServiceImpl leagueService;
	
	@GetMapping("/api/division")
	@ApiOperation(value = "View a list of available divisions", response = List.class)	
	public List<Division> getAllDivision() 
	{
		return leagueService.findAllDivisions();
	}
	
	@GetMapping("/api/league")
	@ApiOperation(value = "View a list of available leagues", response = List.class)	
	public List<LeagueModel> getAllLeagues() 
	{
		return leagueService.findAllLeagues();
	}
	
	@GetMapping("/api/league/{id}")
	@ApiOperation(value = "Get an league by Id")
	public ResponseEntity<LeagueModel> getLeagueById(@PathVariable(value = "id") Long id) 
	{
		return ResponseEntity.ok().body(leagueService.findLeague(id));
	}
	
	@PostMapping("/api/league/create")
	@ApiOperation(value = "Add an league")
	public League createLeague(@Valid @RequestBody LeagueModel model) 
	{	
		return leagueService.createLeague(model);
	}
	
	@PutMapping("/api/league/update/{id}")
	@ApiOperation(value = "Update an league")
	public ResponseEntity<League> updateLeague(@PathVariable(value = "id") Long id, @Valid @RequestBody LeagueModel model) 
	{
		if (leagueService.findLeague(id) == null) 
			return new ResponseEntity<League>(HttpStatus.NOT_FOUND);
		return ResponseEntity.ok(leagueService.updateLeague(model, id));	
	}
	
	@DeleteMapping("/api/league/delete/{id}")
	@ApiOperation(value = "Delete an league")
	public Map<String, Boolean> deleteLeague(@PathVariable(value = "id") Long id)
	{
		leagueService.deleteLeague(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}	
	
}
