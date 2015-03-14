package com.fenlonsky.campaign.base.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.Serializable;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;

import com.fenlonsky.campaign.base.model.BaseEntityModel;
import com.fenlonsky.campaign.base.test.BaseAbstractTests;

/**
 * Base class for running DAO tests.
 * 
 */
@ContextConfiguration(locations = {
		"classpath:/applicationContextTest-resources.xml",
		"classpath:/applicationContext-dao.xml" })
public class GenericDaoTestCase<PK extends Serializable, T extends BaseEntityModel, D extends GenericDao<T, PK>>
		extends BaseAbstractTests {
	
	protected D dao;
	protected T entity;
	
	private Class<T> persistentClass;
	
	public GenericDaoTestCase(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void testSave() throws InstantiationException,
			IllegalAccessException {
		this.entity = this.persistentClass.newInstance();
		Date now = new Date();
		this.entity.setDateModified(now);
		T result = this.dao.save(this.entity);
		assertNotNull(result);
		assertEquals(result.getDateModified(), now);
		
	}
	
	@Test
	public void testDelete() {
		try {
			this.entity = this.persistentClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		T result = this.dao.save(this.entity);
		@SuppressWarnings("unchecked")
		PK id = (PK) result.getId();
		assertNotNull(result);
		this.dao.delete(result);
		try {
			result = this.dao.getOne(id);
		} catch (DataAccessException e) {
			logger.error("can not get one entity :" + result);
			result = null;
		} finally {
			assertNull(result);
		}
	}
	
}
