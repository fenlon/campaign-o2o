package com.fenlonsky.campaign.base.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.joda.time.DateTime;

public class BaseEntityModel extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6163675075289529459L;
	
	String entityName;
	
	/**
	 * 实体创建时间
	 */
	protected DateTime dateCreated = DateTime.now();
	
	/**
	 * 实体修改时间
	 */
	protected DateTime dateModified = DateTime.now();
	
	/**
	 * 实体是否被删除
	 */
	protected Boolean deleted = false;
	
	Long id;
	
	public String getEntityName() {
		return entityName;
	}
	
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	
	public DateTime getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(DateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public DateTime getDateModified() {
		return dateModified;
	}
	
	public void setDateModified(DateTime dateModified) {
		this.dateModified = dateModified;
	}
	
	public Boolean getDeleted() {
		return deleted;
	}
	
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	@Override
	public int hashCode() {
		if (this.id == null) {
			this.id = Long.valueOf(0);
		}
		return HashCodeBuilder.reflectionHashCode(this.id);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
	
}
