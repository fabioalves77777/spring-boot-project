package com.project.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    public Long getId() 
    {
    	return id; 
	} 
    public void setId(Long id) 
    { 
    	this.id = id; 
	} 
    
    @Override  
    public boolean equals(Object object) 
    {    
        if(this == object) return true;
        if(this == null || getClass() != object.getClass()) return false;         
        BaseEntity entity = (BaseEntity) object;          
        return Objects.equals(id, entity.id);
    }
    
    @Override
    public int hashCode() 
    {
        return Objects.hash(id);
    }
    
}