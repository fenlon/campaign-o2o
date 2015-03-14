package com.fenlonsky.campaign.base.web.spring.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenlonsky.campaign.base.model.BaseTreeEntityModel;
import com.fenlonsky.campaign.base.service.GenericTreeManager;

@NoRepositoryBean
public abstract class GenericTreeController<T extends BaseTreeEntityModel<T>, PK extends Serializable, M extends GenericTreeManager<T, PK>>
		extends GenericController<T, PK, M> {
	
	protected M treeManager;
	
	/**
	 * 得到树结构;
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTree.json", method = RequestMethod.GET, produces = "application/json")
	public List<T> getTree(HttpServletRequest request,
			HttpServletResponse response) {
		List<T> result = null;
		String nodeId = request.getParameter("id");
		if (StringUtils.isBlank(nodeId)) {
			result = this.treeManager.getRoot();
		} else {
			T node = this.treeManager.findById(id);
			result = node.getChildren();
		}
		return result;
	}
	
	/**
	 * 得到树结构;
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getChildren/{id}", method = RequestMethod.GET, produces = "application/json")
	public List<T> getChildren(@PathVariable PK id) {
		List<T> result = this.treeManager.getChildren(id);
		return result;
	}
}
