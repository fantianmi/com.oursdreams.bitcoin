package com.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.Btc_user;
import com.mvc.service.UserService;

@Controller
@RequestMapping("/validate.htm")
public class ValidateController {
	@Autowired
	private UserService us;
	protected final transient Log log = LogFactory.getLog(ValidateController.class);

	@RequestMapping(params = "mailvalidate")
	public String vertify(
			@RequestParam("code")String code, 
			@RequestParam("username")String username, 
			HttpServletRequest request, ModelMap modelMap, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Btc_user user = us.getByUsername(username);
		if(user.getUvalidateCode().equals(code)){
			user.setUstatus("active");
			us.updateUser(user);
			if(session.getAttribute("globaluser")!=null){
				session.setAttribute("globaluser", user);
			}
			request.setAttribute("msg", ""+username+"激活成功，现在可以在平台自由交易");
			request.setAttribute("href", "index.htm");
			return "redirect";
		}else{
			request.setAttribute("msg", ""+username+"激活失败，具体情况请联系客服人员或人工激活");
			request.setAttribute("href", "index.htm");
			return "redirect";
		}
	}
}
