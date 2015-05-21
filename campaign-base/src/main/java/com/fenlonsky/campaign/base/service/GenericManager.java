package com.fenlonsky.campaign.base.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenlonsky.campaign.base.model.BaseEntityModel;

/**
 * Generic Manager that talks to GenericDao to CRUD POJOs.
 * 
 * <p>
 * Extend this interface if you want type safe (no casting necessary) managers
 * for your domain objects.
 * 
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
public interface GenericManager<T extends BaseEntityModel, PK extends Serializable> {
	
	/**
	 * 
	 * @param page
	 * @return
	 */
	public Page<T> findAll(Pageable page);
	
	/**
	 * fetch all entities
	 * 
	 * @return list for all entities
	 */
	public List<T> findAll();
	
	/**
	 * fetch specified entity according id;
	 * 
	 * @param id
	 *            entity id;
	 * @return entity
	 */
	public T findById(PK id);
	
	/**
	 * save specified entity;
	 * 
	 * @param entity
	 *            entity for saving
	 * @return saved domain entity
	 */
	public T save(T entity);
	
	/**
	 * save use data which not null
	 * 
	 * @param entity
	 *            entity for saving
	 * @return saved domain entity
	 */
	public T saveSelective(T entity);
	
	/**
	 * 
	 * @param entities
	 * @return
	 */
	public List<T> save(Iterable<T> entities);
	
	/**
	 * * delete entity according given id
	 * 
	 * @param id
	 */
	public Boolean delete(PK id);
	
	/**
	 * remove entity according given id
	 * 
	 * @return
	 */
	public Boolean remove(PK id);
	
	/**
	 * update entity
	 * 
	 * @param entity
	 * @return
	 */
	public T update(T entity);
	
}
