package com.mvc.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chaoshi.htm")
public class ChaoshiController {

	protected final transient Log log = LogFactory.getLog(ChaoshiController.class);
	
	@RequestMapping
	public String chaoshi(
			HttpServletRequest request,
			HttpServletResponse response){
		return "chaoshi";
	}
}
