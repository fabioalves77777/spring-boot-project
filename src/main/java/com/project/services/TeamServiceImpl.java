package com.project.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.Coach;
import com.project.entity.Division;
import com.project.entity.League;
import com.project.entity.Player;
import com.project.entity.Team;
import com.project.model.CoachModel;
import com.project.model.LeagueModel;
import com.project.model.TeamModel;
import com.project.repository.CoachRepository;
import com.project.repository.GoalRepository;
import com.project.repository.TeamRepository;

@Service
public class TeamServiceImpl extends CrudServiceImpl<Team, TeamModel>
{
	@Autowired
	private CoachServiceImpl coachService;
	@Autowired
	private CoachRepository coachRepository;
	@Autowired
	private PlayerServiceImpl playerService;
	@Autowired
	private GoalRepository goalRepository;
	@Autowired
	private LeagueServiceImpl leagueService;
	@Autowired
	private TeamRepository teamRepository;
	
	public List<CoachModel> findAllCoachsNotTeam()
	{
		return coachService.findAllCoachs().stream().filter(c -> c.getTeam() == null).collect(Collectors.toList());
	}
	
	public List<Player> findAllPlayersNotTeam()
	{
		return playerService.findAllPlayers().stream().filter(p -> p.getTeam() == null).collect(Collectors.toList());
	}
	
	public List<Player> findAllTeamPlayers(Long id)
	{
		return playerService.findAllPlayers().stream().filter(p -> p.getTeam().getId() == id).collect(Collectors.toList());
	}
	
	public Long sumGoalsTeam(Long id) 
	{
		return goalRepository.findAll().stream().filter(g -> g.getPlayer().getTeam().getId() == id).count();
	}
	
	public List<LeagueModel> findAllLeagues()
	{
		return leagueService.findAllLeagues();
	}
	
	public Set<Division> getDivisionsForLeague(String name)
	{
		return leagueService.findLeagueByName(name).getDivisions();
	}
	
	public List<TeamModel> getTeamsFromLeagueAndDivision(String name, String division)
	{
		League league = leagueService.findLeagueByName(name);
		return findAllTeams().stream().filter(t -> t.getLeague().equals(league.getName()) && t.getDivision().equals(division)).collect(Collectors.toList());
	}
	
	public Team addCoach(TeamModel model) 
	{
		return saveCoach(model);
	}
	
	public Team addPlayers(TeamModel model) 
	{
		return savePlayers(model);
	}
	
	public List<TeamModel> findAllTeams()
	{
		return findAll();
	}
	
	public TeamModel findTeam(Long id) 
	{
		return findOne(id);
	}
	
	public Team createTeam(TeamModel model) 
	{
		return save(model, null);
	}
	
	public Team updateTeam(TeamModel model, Long id) 
	{
		return save(model, id);
	}
	
	public void deleteTeam(Long id) 
	{
		delete(id);
	}	
	
	public Team save(TeamModel model, Long id) 
	{
		Team team = new Team(model.getName());
		if(id != null) team.setId(id);
		team.setLeague(leagueService.findLeagueByName(model.getLeague()));
		team.setDivision(leagueService.findDivisionByName(model.getDivision()));
		return teamRepository.save(team);
	}
	
	public Team saveCoach(TeamModel model) 
	{
		Team team = teamRepository.findById(model.getId()).orElse(null);
		Coach coach = coachService.findCoachByName(model.getCoach());		
		if(model.isDismiss()) 
		{
			team.setCoach(null);
			coach.setTeam(null);
		}
		else 
		{
			team.setCoach(coach);
			coach.setTeam(team);
		}			
		coachRepository.save(coach);
		return teamRepository.save(team);		
	}
	
	public Team savePlayers(TeamModel model) 
	{
		Team team = teamRepository.findById(model.getId()).orElse(null);
		Set<Player> players = model.getPlayers();		
		if(model.isDismiss()) 
		{
			players.forEach(player -> {
				team.getPlayers().remove(player);
				player.setTeam(null); 
				playerService.save(player);
			});
		} 
		else 
		{
			players.forEach(player -> { 
				player.setTeam(team); 
				playerService.save(player);
			});
			team.setPlayers(players);
		}		
		return teamRepository.save(team);	
	}
	
}
