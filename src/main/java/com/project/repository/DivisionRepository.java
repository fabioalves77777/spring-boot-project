package com.project.repository;

import org.springframework.stereotype.Repository;

import com.project.entity.Division;

@Repository
public interface DivisionRepository extends BaseRepository<Division>
{
	Division findByName(String name);
}
