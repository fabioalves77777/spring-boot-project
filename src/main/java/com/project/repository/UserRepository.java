package com.project.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.project.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User>
{
	Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}