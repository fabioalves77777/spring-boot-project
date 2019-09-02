package com.project.repository;

import org.springframework.stereotype.Repository;

import com.project.entity.League;

@Repository
public interface LeagueRepository extends BaseRepository<League>
{
	League findByName(String name);	
}
