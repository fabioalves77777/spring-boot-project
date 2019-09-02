package com.project.repository;

import org.springframework.stereotype.Repository;

import com.project.entity.Player;

@Repository
public interface PlayerRepository extends BaseRepository<Player>
{
	Player findByName(String name);
}