package com.fenlonsky.campaign.base.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fenlonsky.campaign.base.dao.GenericDao;
import com.fenlonsky.campaign.base.dao.mybatis.utils.DataUtil;
import com.fenlonsky.campaign.base.dao.utils.PKgen;
import com.fenlonsky.campaign.base.model.BaseEntityModel;

public class GenericDaoImpl<T extends BaseEntityModel> implements GenericDao<T, Long> {
	
	public static final String FIND_ALL = "findAll";
	public static final String FIND_ALL_BY_SORT = "findAllBySort";
	public static final String FIND_ALL_BY_PAGE = "findAllByPage";
	public static final String FIND_BY_ID = "selectById";
	public static final String CREATE = "insert";
	public static final String CREATE_SELECTIVE = "insertSelective";
	public static final String COUNT = "count";
	private static final String DELETE_BY_ID = "deleteById";
	private static final String REMOVE_BY_ID = "removeById";
	private static final String UPDATE_BY_ID_SELECTIVE = "updateByIdSelective";
	private static final String UPDATE_BY_ID = "updateById";
	
	@Autowired
	protected SqlSessionTemplate template;
	private Class<T> type;
	
	public GenericDaoImpl() {
		this.type = this.getDAOClass();
	}
	
	@SuppressWarnings("unchecked")
	private Class<T> getDAOClass() {
		Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return clazz;
	}
	
	@Override
	public List<T> findAll() {
		return this.template.selectList(type.getName() + "." + FIND_ALL);
	}
	
	@Override
	public List<T> findAll(Sort sort) {
		return this.template.selectList(type.getName() + "." + FIND_ALL_BY_SORT, sort);
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public T getOne(Long Id) {
		return this.template.selectOne(type.getName() + "." + FIND_BY_ID, Id);
	}
	
	@Override
	public Page<T> findAll(Pageable pageable) {
		// 不应该在这里判断分页对象是否为空，应该在controller层去做控制
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", pageable);
		List<T> content = this.template.selectList(type.getName() + "." + FIND_ALL_BY_PAGE, map);
		Long total = DataUtil.getTotalCount();
		return new PageImpl<T>(content, pageable, total);
	}
	
	@Override
	public long count() {
		return this.template.selectOne(type.getName() + "." + COUNT);
	}
	
	@Override
	public void delete(Long Id) {
		this.template.delete(type.getName() + "." + DELETE_BY_ID, Id);
	}
	
	@Override
	public void delete(T record) {
		
		this.template.delete(type.getName(), record);
	}
	
	@Override
	public void delete(Iterable<? extends T> records) {
		this.template.delete(type.getName(), records);
	}
	
	@Override
	public void deleteAll() {
		this.template.selectList(type.getSimpleName());
	}
	
	@Override
	public boolean exists(Long Id) {
		if (findOne(Id) == null) {
			return false;
		}
		return true;
	}
	
	@Override
	public T findOne(Long Id) {
		return this.template.selectOne(type.getName() + "." + FIND_BY_ID, Id);
	}
	
	@Override
	public <S extends T> S save(S record) {
		record.setId(PKgen.getInstance().nextPK());
		if (this.template.insert(type.getName() + "." + CREATE, record) > 0) {
			return record;
		}
		return null;
	}
	
	@Override
	public T saveSelective(T record) {
		record.setId(PKgen.getInstance().nextPK());
		if (this.template.insert(type.getName() + "." + CREATE_SELECTIVE, record) > 0) {
			return record;
		}
		return null;
	}
	
	@Override
	public T updateByIdSelective(T record) {
		return this.template.update(type.getName() + "." + UPDATE_BY_ID_SELECTIVE, record) > 0 ? record : null;
	}
	
	@Override
	public T updateById(T record) {
		return this.template.update(type.getName() + "." + UPDATE_BY_ID, record) > 0 ? record : null;
	}
	
	@Override
	public Boolean remove(Long id) {
		return this.template.delete(type.getName() + "." + REMOVE_BY_ID, id) > 0;
	}
	
}
