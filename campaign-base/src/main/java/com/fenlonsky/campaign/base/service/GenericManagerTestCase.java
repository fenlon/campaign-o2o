package com.fenlonsky.campaign.base.service;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fenlonsky.campaign.base.model.BaseEntityModel;
import com.fenlonsky.campaign.base.test.BaseAbstractTests;

@ContextConfiguration(locations = {
		"classpath:/applicationContextTest-resources.xml",
		"classpath:/applicationContext-dao.xml",
		"classpath:/applicationContext-ehcache.xml",
		"classpath:/applicationContext-service.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
@Transactional
public abstract class GenericManagerTestCase<PK extends Serializable, T extends BaseEntityModel, M extends GenericManager<T, PK>>
		extends BaseAbstractTests {
	
	protected M manager;
	
	protected T entity;
	
	protected List<T> list;
	
	private Class<T> persistentClass;
	
	public GenericManagerTestCase(final Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	
	/**
	 * A simple logger
	 */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Before
	public void setUp() throws Exception {
		this.entity = this.persistentClass.newInstance();
	}
	
	@Test
	public void testSave() {
		this.entity = this.manager.save(this.entity);
	}
	
}
