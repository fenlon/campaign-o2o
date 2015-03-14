package com.fenlonsky.campaign.base.dao.utils;

/**
 * 
 * @author jgarcia
 */
@SuppressWarnings("serial")
public class SearchException extends RuntimeException {

	public SearchException(Throwable ex) {
		super(ex);
	}

	public SearchException(String msg) {
		super(msg);
	}

}
