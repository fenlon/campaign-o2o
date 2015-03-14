package com.fenlonsky.campaign.base.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.fenlonsky.campaign.base.dao.GenericDao;
import com.fenlonsky.campaign.base.model.BaseEntityModel;

@Repository("GenericDao")
public class GenericDaoImpl<T extends BaseEntityModel> implements GenericDao<T, Long> {
	
	public static final String FIND = "find_";
	public static final String FIND_ALL = "findAll_";
	public static final String CREATE = "create_";
	public static final String DELETE = "delete_";
	public static final String UPDATE = "update_";
	public static final String COUNT = "count_";
	@Autowired
	protected SqlSessionTemplate template;
	private Class<T> type;
	
	public GenericDaoImpl() {
		this.type = this.getDAOClass();
	}
	
	@SuppressWarnings("all")
	private Class<T> getDAOClass() {
		Class clazz = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return clazz;
	}
	
	@Override
	public List<T> findAll() {
		return this.template.selectList(type.getSimpleName());
		
	}
	
	@Override
	public List<T> findAll(Sort sort) {
		return this.template.selectList(type.getSimpleName(), sort);
	}
	
	@Override
	public List<T> findAll(Iterable<Long> ids) {
		if (ids == null || !ids.iterator().hasNext()) {
			return Collections.emptyList();
		}
		List<T> result = new ArrayList<T>();
		for (Long id : ids) {
			result.add(findOne(id));
		}
		return result;
	}
	
	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		List<S> result = new ArrayList<S>();
		
		if (entities == null) {
			return result;
		}
		
		for (S entity : entities) {
			result.add(save(entity));
		}
		
		return result;
	}
	
	@Override
	public void flush() {
		this.template.flushStatements();
	}
	
	@Override
	public <S extends T> S saveAndFlush(S entity) {
		S result = save(entity);
		this.template.flushStatements();
		return result;
	}
	
	@Override
	public void deleteInBatch(Iterable<T> entities) {
		
	}
	
	@Override
	public void deleteAllInBatch() {
		
	}
	
	@Override
	public T getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<T> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public long count() {
		return this.template.selectOne(type.getSimpleName());
	}
	
	@Override
	public void delete(Long ID) {
		this.template.delete(type.getSimpleName(), ID);
	}
	
	@Override
	public void delete(T obj) {
		this.template.delete(type.getSimpleName(), obj);
		
	}
	
	@Override
	public void delete(Iterable<? extends T> objs) {
		this.template.delete(type.getSimpleName(), objs);
	}
	
	@Override
	public void deleteAll() {
		this.template.selectList(type.getSimpleName());
	}
	
	@Override
	public boolean exists(Long ID) {
		if (this.template.selectOne(type.getSimpleName(), ID) == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public T findOne(Long ID) {
		return this.template.selectOne(type.getSimpleName(), ID);
		
	}
	
	@Override
	public <S extends T> S save(S s) {
		if (this.template.insert(type.getSimpleName(), s) > 0) {
			return s;
		}
		return null;
	}
}
