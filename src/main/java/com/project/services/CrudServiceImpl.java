package com.project.services;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.project.entity.BaseEntity;
import com.project.repository.BaseRepository;

public class CrudServiceImpl<TEntity extends BaseEntity, TModel> implements CrudService<TEntity, TModel>
{
    @Autowired
    protected BaseRepository<TEntity> repository;
 
    @Bean
    public ModelMapper modelMapper() 
    {
        return new ModelMapper();
    }
    
    @Autowired
    protected ModelMapper modelMapper;
 
    protected Class<TEntity> entityClass;
    protected Class<TModel> modelClass;
 
    @SuppressWarnings("unchecked")
    public CrudServiceImpl()
    {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<TEntity>) genericSuperclass.getActualTypeArguments()[0];
        this.modelClass = (Class<TModel>) genericSuperclass.getActualTypeArguments()[1];
    }
 
    public List<TModel> findAll()
    {
        List<TModel> result = new ArrayList<>();
        List<TEntity> entities = repository.findAll();
        for (TEntity entity : entities)
        	result.add(modelMapper.map(entity, modelClass));
        return result;
    }
    
    public TModel findOne(Long id)
    {
        Optional<TEntity> entity = repository.findById(id);
        return modelMapper.map(entity.get(), modelClass);
    }
    
    public TModel create(TModel model)
    {
        TEntity entity = modelMapper.map(model, entityClass);
        repository.saveAndFlush(entity);
        return model;
    }
 
    public TModel update(TModel model)
    {
        TEntity entity = modelMapper.map(model, entityClass);
        repository.save(entity);
        return model;
    }
 
    public void delete(Long id)
    {
        Optional<TEntity> entity = repository.findById(id);
        repository.delete(entity.get());
    }   

}
