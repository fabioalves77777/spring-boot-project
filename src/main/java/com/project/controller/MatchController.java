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

import com.project.model.MatchModel;
import com.project.services.MatchServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Match Management System")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class MatchController 
{
	@Autowired
	private MatchServiceImpl matchService;
	
	@GetMapping("/api/match")
	@ApiOperation(value = "View a list of available matchs", response = List.class)	
	public List<MatchModel> getAllMatchs() 
	{
		return matchService.findAllMatchs();
	}
	
	@GetMapping("/api/match/{id}")
	@ApiOperation(value = "Get an match by Id")
	public ResponseEntity<MatchModel> getMatchById(@PathVariable(value = "id") Long id) 
	{
		return ResponseEntity.ok().body(matchService.findMatch(id));
	}
	
	@PostMapping("/api/match/create")
	@ApiOperation(value = "Add an match")
	public MatchModel createMatch(@Valid @RequestBody MatchModel model) 
	{
		return matchService.createMatch(model);
	}
	
	@PutMapping("/api/match/update/{id}")
	@ApiOperation(value = "Update an match")
	public ResponseEntity<MatchModel> updateMatch(@PathVariable(value = "id") Long id, @Valid @RequestBody MatchModel model) 
	{
		if (matchService.findMatch(id) == null) 
			return new ResponseEntity<MatchModel>(HttpStatus.NOT_FOUND); 
		return ResponseEntity.ok(matchService.updateMatch(model));	
	}
	
	@DeleteMapping("/api/match/delete/{id}")
	@ApiOperation(value = "Delete an match")
	public Map<String, Boolean> deleteMatch(@PathVariable(value = "id") Long id)
	{
		matchService.deleteMatch(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
