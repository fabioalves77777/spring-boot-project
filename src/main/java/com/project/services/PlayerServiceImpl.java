package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Position;
import com.project.entity.Player;
import com.project.model.PlayerModel;
import com.project.repository.PlayerRepository;
import com.project.repository.PositionRepository;

@Service
public class PlayerServiceImpl extends CrudServiceImpl<Player, PlayerModel>
{ 
	@Autowired
	private PlayerRepository playerRepository;
	@Autowired
	private PositionRepository positionRepository;
	
	public List<Position> findAllPositions()
	{
		return positionRepository.findAll();
	}
	
	public List<Player> findAllPlayers()
	{
		return playerRepository.findAll();
	}
	
	public Player findPlayer(Long id) 
	{
		return playerRepository.findById(id).orElse(null);
	}
	
	public Player createPlayer(PlayerModel model) 
	{
		return save(model, null);
	}
	
	public Player updatePlayer(PlayerModel model, Long id) 
	{
		return save(model, id);
	}
	
	public void deletePlayer(Long id) 
	{
		delete(id);
	}
	
	public Player save(PlayerModel model, Long id) 
	{				
		Player player = new Player(model.getName());
		if(id != null) player.setId(id);
		player.setPosition(positionRepository.findByName(model.getPosition()));
		return playerRepository.save(player);
	}
	
	public Player save(Player player) 
	{				
		return playerRepository.save(player);
	}
	
}