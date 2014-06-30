package com.mvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.mvc.entity.Btc_user;
import com.mvc.service.CotentService;
import com.mvc.service.StockService;
import com.mvc.service.TradeCateService;
import com.mvc.service.UserService;
import com.mvc.util.MD5Util;
import com.mvc.vo.NaviStockModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/vertify.htm"})
public class LoginController
{

  @Autowired
  private UserService us;

  @Autowired
  private MD5Util md5util;
  @Autowired
  private StockService stockService;
	@Autowired
	private TradeCateService tradecates = new TradeCateService();
  @Autowired
  private CotentService contents;
  protected final transient Log log = LogFactory.getLog(LoginController.class);

  @RequestMapping
  public String vertify(HttpServletRequest request, HttpServletResponse response) throws IOException { 
  	HttpSession session = request.getSession();
		Map<String,List<Object>> nav = tradecates.getNav();
		session.setAttribute("nav",nav);
		session.setAttribute("newslist", contents.getNewsLimit());
		session.setAttribute("newslistall", contents.getNewsCAll());
		session.setAttribute("indexmap", contents.getIndexContent(10000001));
		Map<Integer, Object> allstockmap = stockService.getBtc_stockByState(1);
		session.setAttribute("allstockmap", allstockmap);
		Map<Integer,Object> stockmap = stockService.getBtc_stockMapbyId();
		session.setAttribute("stockmap", stockmap);
		session.setAttribute("stoc_kmap", stockmap);
		
		Map<String, NaviStockModel> stock_map_navigation = stockService.getBtc_stockByExchangeStockName("CNY");
		session.setAttribute("stock_map_navigation", stock_map_navigation);
		int user_amount = us.countAllUser();
		session.setAttribute("user_amount", user_amount);

    String uusername = request.getParameter("uusername");
    String upassword = request.getParameter("upassword");
    
    boolean flag = false;
    if (this.us.getByUsername(uusername) == null) {
      request.setAttribute("msg", "用户名不存在");
      request.setAttribute("href", "back");
      return "login";
    }
    Btc_user vuser = this.us.getByUsername(uusername);
    flag = MD5Util.validate(upassword, vuser.getUpassword());
    if ((uusername != null) && (upassword != null)) {
      if (flag) {
        session.setAttribute("globaluser", vuser);
        response.sendRedirect("index.htm");
        return "index";
      }
      request.setAttribute("msg", "用户名或者密码错误");
      request.setAttribute("href", "index.htm");
      return "login";
    }

    return "login"; }

  @RequestMapping(params={"Login"})
  public String gLogin(ModelMap modelMap)
  {
    return "login";
  }
  @RequestMapping(params={"Register"})
  public String register(ModelMap modelMap) { return "register";
  }

  @RequestMapping(params={"qqLogin"})
  public void qqLogin(ModelMap modelMap)
  {
  }
}