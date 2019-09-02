package com.project.repository;

import org.springframework.stereotype.Repository;

import com.project.entity.Position;

@Repository
public interface PositionRepository extends BaseRepository<Position>
{
	Position findByName(String position);
}
