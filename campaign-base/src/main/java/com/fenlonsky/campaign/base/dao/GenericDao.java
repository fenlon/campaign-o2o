package com.fenlonsky.campaign.base.dao;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.fenlonsky.campaign.base.model.BaseEntityModel;

/**
 * Generic DAO (Data Access Object) with common methods to CRUD POJOs.
 * Implemented with Spring-Data-JPA Repository
 * <p>
 * Extend this interface if you want type safe (no casting necessary) DAO's for
 * your domain objects.
 * 
 * @author <a href="mailto:ming616@gmail.com">Liu Xiaoming</a>
 * @param <T>
 *            a type variable,实体类型
 * @param <PK>
 *            the primary key for that type，实体类Id
 */
@NoRepositoryBean
public interface GenericDao<T extends BaseEntityModel, PK extends Serializable>
		extends JpaRepository<T, PK> {
	
	T saveSelective(T record);
	
	T updateByIdSelective(T record);
	
	T updateById(T record);
	
	Boolean remove(PK id);
	
	T findByCondition(String statement, Map<String, Object> params);
	
}
