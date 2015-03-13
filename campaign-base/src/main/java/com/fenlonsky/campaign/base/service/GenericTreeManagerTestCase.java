package com.fenlonsky.campaign.base.service;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.fenlonsky.campaign.base.model.BaseTreeEntityModel;

public abstract class GenericTreeManagerTestCase<PK extends Serializable, T extends BaseTreeEntityModel<T>, M extends GenericTreeManager<T, PK>>
		extends GenericManagerTestCase<PK, T, M> {
	public GenericTreeManagerTestCase(Class<T> persistentClass) {
		super(persistentClass);
	}

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(GenericTreeManagerTestCase.class);

	@Test
	public void testGetRoot() {
		List<T> result = this.manager.getRoot();
		if (logger.isInfoEnabled()) {
			logger.info("testGetRoot() - List<T> result=" + result); //$NON-NLS-1$
		}

	}

}
