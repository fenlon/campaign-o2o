package com.fenlonsky.campaign.base.web.spring.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fenlonsky.campaign.base.model.BaseEntityModel;
import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.service.GenericManager;

/**
 * 
 * @ClassName: GenericController 抽象的controller实现类
 * @Description: 抽象的controller实现类，各自的controller可以继承该抽象类
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年4月26日 上午10:24:37
 * 
 * @param <T>Model类（实体类）
 * @param <PK>主键ID
 * @param <M>service层的类
 */
public abstract class GenericController<T extends BaseEntityModel, PK extends Serializable, M extends GenericManager<T, PK>>
		extends BaseController {
	
	// protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	@RequestMapping(value = "/data.json", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public APIResult<Map<String, Object>> create(@RequestBody @Valid T model, BindingResult result) {
		
		Map<String, Object> checkRes = checkIfHasError(result);
		if (checkRes.size() > 0) {
			return asError("数据验证失败", checkRes);
		}
		
		this.model = model;
		DateTime date = DateTime.now();
		this.model.setDateCreated(date);
		this.model.setDateModified(date);
		this.model = this.manager.save(this.model);
		checkRes.put("model", this.model);
		return asSuccess(checkRes);
	}
	
	/**
	 * 根据指定id，删除实体对象
	 * 
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public APIResult<String> delete(@PathVariable PK id) {
		// return this.manager.delete(id);
		try {
			this.manager.remove(id);
			return asSuccess("删除数据成功!");
		} catch (Exception e) {
			this.logger.error("数据删除失败:", e.fillInStackTrace());
			return asError("数据删除失败");
		}
	}
	
	/**
	 * 根据输入，返回分页结果中的当前页，包括当前页信息和其中的实体对象集合
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	// 1
	@RequestMapping(value = "/queryByPage.json", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public APIResult<Page<T>> get(@RequestParam("pageNumber") String page, @RequestParam("pageSize") String limit) {
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
		try {
			this.page = this.manager.findAll(this.pageable);
			return asSuccess(this.page);
		} catch (Exception e) {
			logger.info("获取分页数据失败", e.fillInStackTrace());
			return asError("获取分页数据失败", null);
		}
	}
	
	/**
	 * 查询所有的实体对象
	 * 
	 * @return
	 */
	@RequestMapping(value = "/query.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public APIResult<List<T>> getAll() {
		try {
			List<T> list = this.manager.findAll();
			return asSuccess(list);
		} catch (Exception e) {
			logger.info("获取所有数据失败", e.fillInStackTrace());
			return asError("获取所有数据失败", null);
		}
	}
	
	/**
	 * 根据指定的id，获取实体对象
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public APIResult<T> get(@PathVariable PK id) {
		
		try {
			T t = this.manager.findById(id);
			return asSuccess(t);
		} catch (Exception e) {
			logger.info("获取数据失败:", e.fillInStackTrace());
			return asError("获取数据失败", null);
		}
		
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
	public APIResult<T> update(@PathVariable PK id, @RequestBody T model) {
		try {
			model.setId(Long.valueOf(id.toString()));
			model.setDateModified(DateTime.now());// 更新修改时间
			this.model = this.manager.update(model);
			return asSuccess(this.model);
		} catch (Exception e) {
			logger.info("跟新数据失败:", e.fillInStackTrace());
			return asError("跟新数据失败", null);
		}
	}
}
