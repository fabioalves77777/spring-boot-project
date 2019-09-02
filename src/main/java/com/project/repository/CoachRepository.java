package com.project.repository;

import org.springframework.stereotype.Repository;

import com.project.entity.Coach;

@Repository
public interface CoachRepository extends BaseRepository<Coach>
{
	Coach findByName(String name);
}
