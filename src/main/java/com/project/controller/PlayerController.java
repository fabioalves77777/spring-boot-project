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

import com.project.entity.Player;
import com.project.entity.Position;
import com.project.model.PlayerModel;
import com.project.services.PlayerServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Player Management System")
@CrossOrigin(origins = "*", maxAge = 3600)
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class PlayerController 
{
	@Autowired
	private PlayerServiceImpl playerService;
	
	@GetMapping("/api/position")
	@ApiOperation(value = "View a list of available positions", response = List.class)	
	public List<Position> getAllPositions() 
	{
		return playerService.findAllPositions();
	}
	
	@GetMapping("/api/player")
	@ApiOperation(value = "View a list of available players", response = List.class)	
	public List<Player> getAllPlayers() 
	{
		return playerService.findAllPlayers();
	}
	
	@GetMapping("/api/player/{id}")
	@ApiOperation(value = "Get an players by Id")
	public ResponseEntity<Player> getPlayerById(@PathVariable(value = "id") Long id) 
	{
		return ResponseEntity.ok().body(playerService.findPlayer(id));
	}
	
	@PostMapping("/api/player/create")
	@ApiOperation(value = "Add an players")
	public Player createPlayer(@Valid @RequestBody PlayerModel model) 
	{
		return playerService.createPlayer(model);
	}
	
	@PutMapping("/api/player/update/{id}")
	@ApiOperation(value = "Update an player")
	public ResponseEntity<Player> updatePlayer(@PathVariable(value = "id") Long id, @Valid @RequestBody PlayerModel model) 
	{
		if (playerService.findPlayer(id) == null) 
			return new ResponseEntity<Player>(HttpStatus.NOT_FOUND); 
		return ResponseEntity.ok(playerService.updatePlayer(model, id));	
	}
	
	@DeleteMapping("/api/player/delete/{id}")
	@ApiOperation(value = "Delete an player")
	public Map<String, Boolean> deletePlayer(@PathVariable(value = "id") Long id)
	{
		playerService.deletePlayer(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
}
