package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.project.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<TEntity extends BaseEntity> extends JpaRepository<TEntity, Long>
{
}
