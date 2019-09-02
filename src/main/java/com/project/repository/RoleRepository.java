package com.project.repository;

import org.springframework.stereotype.Repository;

import com.project.entity.Role;

@Repository
public interface RoleRepository extends BaseRepository<Role>
{
	Role findByName(String name);
}
