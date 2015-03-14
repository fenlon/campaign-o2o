package com.fenlonsky.campaign.base.web.spring.controller;

import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {
		"classpath:/applicationContextTest-resources.xml",
		"classpath:/applicationContext-dao.xml",
		"classpath:/applicationContext-service.xml",
		"classpath:/applicationContext-ehcache.xml",
		"classpath:/applicationContext-controller.xml" })
@RunWith(value = SpringJUnit4ClassRunner.class)
public abstract class GenericControllerTestCase {
	/**
	 * Convenience methods to make tests simpler
	 * 
	 * @param url
	 *            the URL to post to
	 * @return a MockHttpServletRequest with a POST to the specified URL
	 */
	public MockHttpServletRequest newPost(String url) {
		return new MockHttpServletRequest("POST", url);
	}

	/**
	 * Convenience methods to make tests simpler
	 * 
	 * @param url
	 *            the URL to post to
	 * @return a MockHttpServletRequest with a GET to the specified URL
	 */
	public MockHttpServletRequest newGet(String url) {
		return new MockHttpServletRequest("GET", url);
	}

	protected MockHttpServletResponse response = new MockHttpServletResponse();
}
