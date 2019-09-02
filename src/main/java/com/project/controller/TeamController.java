package com.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.project.entity.Player;
import com.project.entity.Team;
import com.project.model.CoachModel;
import com.project.model.LeagueModel;
import com.project.model.TeamModel;
import com.project.services.TeamServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Team Management System")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class TeamController 
{
	@Autowired
	private TeamServiceImpl teamService;
	
	@GetMapping("/api/team/coachs-not-team")
	@ApiOperation(value = "View a list of available coachs not team", response = List.class)	
	public List<CoachModel> getAllCoachsNotTeam()
	{
		return teamService.findAllCoachsNotTeam();
	}
	
	@GetMapping("/api/team/players-not-team")
	@ApiOperation(value = "View a list of available players not team", response = List.class)	
	public List<Player> getAllPlayersNotTeam()
	{
		return teamService.findAllPlayersNotTeam();
	}
	
	@GetMapping("/api/team/team-players/{id}")
	@ApiOperation(value = "View a list of available players not team", response = List.class)	
	public List<Player> getAllTeamPlayers(@PathVariable(value = "id") Long id)
	{
		return teamService.findAllTeamPlayers(id);
	}
	
	@GetMapping("/api/team/sum-goals-team/{id}")
	@ApiOperation(value = "Get sum players goals by Id team")
	public Long getSumGoalsTeam(@PathVariable(value = "id") Long id) 
	{
		return teamService.sumGoalsTeam(id);
	}
	
	@GetMapping("/api/team/leagues")
	@ApiOperation(value = "View a list of available leagues")
	public List<LeagueModel> getAllLeagues()
	{
		return teamService.findAllLeagues();
	}
	
	@GetMapping("/api/team/divisions-for-league/{name}")
	@ApiOperation(value = "Get an divisions league by name")	
	public Set<Division> getDivisionsForLeague(@PathVariable(value = "name") String name)
	{
		return teamService.getDivisionsForLeague(name);		
	}
	
	@GetMapping("/api/team/teams-league-division/{league}/{division}")
	@ApiOperation(value = "Get an teams from league and division")	
	public List<TeamModel> getTeamsFromLeagueAndDivision(String league, String division)
	{
		return teamService.getTeamsFromLeagueAndDivision(league, division);		
	}
	
	@PutMapping("/api/team/add-coach")
	@ApiOperation(value = "Add coach an team")
	public ResponseEntity<Team> addCoach( @Valid @RequestBody TeamModel model) 
	{
		if (teamService.findTeam(model.getId()) == null) 
			return new ResponseEntity<Team>(HttpStatus.NOT_FOUND); 
		return ResponseEntity.ok(teamService.addCoach(model));	
	}
	
	@PutMapping("/api/team/add-players")
	@ApiOperation(value = "Add players an team")
	public ResponseEntity<Team> addPlayers( @Valid @RequestBody TeamModel model) 
	{
		if (teamService.findTeam(model.getId()) == null) 
			return new ResponseEntity<Team>(HttpStatus.NOT_FOUND); 
		return ResponseEntity.ok(teamService.addPlayers(model));	
	}
	
	@GetMapping("/api/team")
	@ApiOperation(value = "View a list of available teams", response = List.class)	
	public List<TeamModel> getAllTeams() 
	{
		return teamService.findAllTeams();
	}
	
	@GetMapping("/api/team/{id}")
	@ApiOperation(value = "Get an team by Id")
	public ResponseEntity<TeamModel> getTeamById(@PathVariable(value = "id") Long id) 
	{
		return ResponseEntity.ok().body(teamService.findTeam(id));
	}
	
	@PostMapping("/api/team/create")
	@ApiOperation(value = "Add an team")
	public Team createTeam(@Valid @RequestBody TeamModel model) 
	{
		return teamService.createTeam(model);
	}
	
	@PutMapping("/api/team/update/{id}")
	@ApiOperation(value = "Update an team")
	public ResponseEntity<Team> updateTeam(@PathVariable(value = "id") Long id, @Valid @RequestBody TeamModel model) 
	{
		if (teamService.findTeam(id) == null) 
			return new ResponseEntity<Team>(HttpStatus.NOT_FOUND); 
		return ResponseEntity.ok(teamService.updateTeam(model, id));	
	}
	
	@DeleteMapping("/api/team/delete/{id}")
	@ApiOperation(value = "Delete an team")
	public Map<String, Boolean> deleteTeam(@PathVariable(value = "id") Long id)
	{
		teamService.deleteTeam(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
