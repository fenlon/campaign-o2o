package com.fenlonsky.campaign.base.web.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.fenlonsky.campaign.base.protocols.APIResult;
import com.fenlonsky.campaign.base.protocols.APIResultCode;

@Controller
public class BaseController extends MultiActionController {
	
	protected <T> APIResult<T> asResult(Boolean success, APIResultCode code, String message, T t) {
		return new APIResult<T>(success, code, message, t);
	}
	
	protected <T> APIResult<T> asSuccess(T t) {
		return new APIResult<T>(true, APIResultCode.SUCCESS, null, t);
	}
	
	protected APIResult<String> asError(String message) {
		return new APIResult<String>(false, APIResultCode.ERROR, message, null);
	}
	
	protected <T> APIResult<T> asError(String message, T t) {
		return new APIResult<T>(false, APIResultCode.ERROR, message, t);
	}
	
	/**
	 * 检查入参是否正确，不正确将错误返回到前端
	 * 
	 * @param result
	 * @return
	 */
	protected Map<String, Object> checkIfHasError(BindingResult result) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				String field = error.getCodes()[1].substring(error.getCodes()[1].lastIndexOf('.') + 1);
				if (map.containsKey(field)) {
					map.put(field, map.get(field) + ";" + error.getDefaultMessage());
				} else {
					map.put(field, error.getDefaultMessage());
				}
			}
		}
		return map;
	}
}
