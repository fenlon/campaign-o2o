package com.fenlonsky.campaign.qrcode.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/error/")
public class ErrorController {
	
	@RequestMapping(value = "400.html", method = RequestMethod.GET)
	public String error_400() {
		return "/commons/400";
	}
	
	@RequestMapping(value = "404.html", method = RequestMethod.GET)
	public String error_404() {
		return "/commons/404";
	}
	
	@RequestMapping(value = "500.html", method = RequestMethod.GET)
	public String error_500() {
		return "/commons/500";
	}
}
