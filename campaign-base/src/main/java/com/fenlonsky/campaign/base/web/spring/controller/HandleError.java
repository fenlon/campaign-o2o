package com.fenlonsky.campaign.base.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 未捕获异常的处理，需要在web.xml中配置<error-page>
 * 
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年7月31日下午6:03:01
 */
@Controller
@RequestMapping("/error")
public class HandleError {
	
	@RequestMapping(value = "400", method = RequestMethod.GET)
	public ModelAndView error_400(ModelMap modelMap) {
		return new ModelAndView("commons/400", modelMap);
	}
	
	@RequestMapping(value = "404", method = RequestMethod.GET)
	public ModelAndView error_404(ModelMap modelMap) {
		return new ModelAndView("commons/404", modelMap);
	}
	
	@RequestMapping(value = "500", method = RequestMethod.GET)
	public ModelAndView error_500(ModelMap modelMap) {
		return new ModelAndView("commons/500", modelMap);
	}
	
	@RequestMapping(value = "no_permission.html", method = RequestMethod.GET)
	public ModelAndView noPermission(ModelMap modelMap) {
		return new ModelAndView("/commons/no-permission", modelMap);
	}
	
}
