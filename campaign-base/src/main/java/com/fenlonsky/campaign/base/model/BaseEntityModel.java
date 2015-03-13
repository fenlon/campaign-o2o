package com.fenlonsky.campaign.base.model;

import java.util.Date;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BaseEntityModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6163675075289529459L;

	String entityName;

	/**
	 * 实体创建时间
	 */
	protected Date dateCreated = new Date();

	/**
	 * 实体修改时间
	 */
	protected Date dateModified = new Date();

	/**
	 * 实体是否被删除
	 */
	protected Boolean deleted = false;

	Long id;

	@Override
	public boolean equals(Object obj) {
		if (null != obj) {
			if (obj instanceof BaseEntityModel) {
				BaseEntityModel domain = (BaseEntityModel) obj;
				if (this.id == domain.id) {
					return true;
				}
			}
		}
		return false;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		if (this.id == null) {
			this.id = Long.valueOf(0);
		}
		return HashCodeBuilder.reflectionHashCode(this.id);
	}

	public Boolean isDeleted() {
		return deleted;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * public String getFrom() { return from; }
	 */
	/*
	 * public void setFrom(String from) { this.from = from; }
	 */

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
