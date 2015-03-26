package com.fenlonsky.campaign.base.web.spring.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenlonsky.campaign.base.model.BaseEntityModel;
import com.fenlonsky.campaign.base.service.GenericManager;

public abstract class GenericController<T extends BaseEntityModel, PK extends Serializable, M extends GenericManager<T, PK>>
		extends BaseController {
	
	protected M manager;
	protected PK id;
	protected T model;
	protected Page<T> page;
	
	protected int pageNumber = 0;
	protected int pageSize = 20;
	
	protected Pageable pageable = new PageRequest(pageNumber, pageSize,
			new Sort(Direction.ASC, "id"));
	
	/**
	 * 根据输入的实体对象，创建一个新的实体对象
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public T create(@RequestBody T model) {
		this.model = model;
		DateTime date = DateTime.now();
		this.model.setDateCreated(date);
		this.model.setDateModified(date);
		this.model = this.manager.save(this.model);
		return this.model;
	}
	
	/**
	 * 根据指定id，删除实体对象
	 * 
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public void delete(@PathVariable PK id) throws IOException {
		
		this.manager.delete(id);
	}
	
	/**
	 * 根据输入，返回分页结果中的当前页，包括当前页信息和其中的实体对象集合
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	// 1
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Page<T> get(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		String limit = request.getParameter("limit");
		if (StringUtils.isNotBlank(page)) {
			this.pageNumber = Integer.valueOf(page) - 1;
		} else {
			this.pageNumber = 0;
		}
		if (StringUtils.isNotBlank(limit)) {
			this.pageSize = Integer.valueOf(limit);
		}
		this.pageable = new PageRequest(this.pageNumber, this.pageSize,
				new Sort(Direction.ASC, "id"));
		this.page = this.manager.findAll(this.pageable);
		logger.info(this.page);
		return this.page;
	}
	
	/**
	 * 根据指定的id，获取实体对象
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public T get(@PathVariable PK id) {
		return this.manager.findById(id);
		
	}
	
	/**
	 * 根据输入的实体对象信息，修改指定id的实体对象
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public T update(@PathVariable PK id, @RequestBody T model) {
		model.setId(Long.valueOf(id.toString()));
		model.setDateModified(DateTime.now());// 更新修改时间
		this.model = this.manager.save(model);
		return this.model;
	}
	
}
