package com.fenlonsky.campaign.base.model;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.map.ext.JodaSerializers.DateTimeSerializer;
import org.joda.time.DateTime;

import com.fenlonsky.campaign.commons.utils.FenlonDigestUtils;

public class BaseEntityModel extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6163675075289529459L;
	
	String entityName;
	
	String encodeStr;
	
	/**
	 * 实体创建时间
	 */
	@org.codehaus.jackson.map.annotate.JsonSerialize(using = DateTimeSerializer.class)
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
	
	public String getEncodeStr() {
		if (encodeStr == null && id != null) {
			encodeStr = FenlonDigestUtils.pbeEncrypt(id + "");
			return encodeStr;
		}
		return encodeStr;
	}
	
	public void setEncodeStr() {
		if (id != null) {
			this.encodeStr = FenlonDigestUtils.pbeEncrypt(id + "");
		}
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
		setEncodeStr();
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
