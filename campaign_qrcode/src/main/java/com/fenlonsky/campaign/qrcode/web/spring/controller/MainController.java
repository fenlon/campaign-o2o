package com.fenlonsky.campaign.qrcode.web.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class MainController {
	
	@RequestMapping(value = "index.html", method = RequestMethod.GET)
	public String index() {
		return "/qrcode/main/index";
	}
}
