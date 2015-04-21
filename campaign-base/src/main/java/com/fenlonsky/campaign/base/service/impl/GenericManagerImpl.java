package com.fenlonsky.campaign.base.service.impl;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.fenlonsky.campaign.base.dao.GenericDao;
import com.fenlonsky.campaign.base.model.BaseEntityModel;
import com.fenlonsky.campaign.base.service.GenericManager;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>
 * To register this class in your Spring context file, use the following XML.
 * 
 * 
 * 
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
@Transactional
public class GenericManagerImpl<T extends BaseEntityModel, PK extends Serializable>
		implements GenericManager<T, PK> {
	
	protected GenericDao<T, PK> dao;
	
	@Override
	public Page<T> findAll(Pageable page) {
		return this.dao.findAll(page);
	}
	
	@Override
	public List<T> findAll() {
		return this.dao.findAll();
	}
	
	@Override
	public T findById(PK id) {
		return this.dao.findOne(id);
	}
	
	@Override
	public T save(T entity) {
		DateTime date = DateTime.now();
		Long id = entity.getId();
		if (id == null) {
			entity.setDateCreated(date);
		} else {
			entity.setDateModified(date);
		}
		return this.dao.saveAndFlush(entity);
	}
	
	@Override
	public List<T> save(Iterable<T> entities) {
		return this.dao.save(entities);
	}
	
	@Override
	public void delete(PK id) {
		this.dao.delete(id);
	}
	
	@Override
	public T update(T entity) {
		return this.dao.updateByIdSelective(entity);
	}
	
}
