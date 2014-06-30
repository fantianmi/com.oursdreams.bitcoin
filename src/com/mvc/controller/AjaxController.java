package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.service.DealService;
import com.mvc.vo.Btc_deal_list_vo;

@Controller
@RequestMapping("/ajax.htm")
public class AjaxController {
	@Autowired
	private DealService ds = new DealService();

	protected final transient Log log = LogFactory.getLog(AjaxController.class);
	
	@RequestMapping(params = "hicharts")
	public void loadRereshByStock(
			@RequestParam("stockId") int stockId,
			@RequestParam("exstock") String exstock,
			@RequestParam("type") String type,
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		List<Btc_deal_list_vo> btc_deal_list_vo = new ArrayList<Btc_deal_list_vo>();
		if(type.equals("min")){
			btc_deal_list_vo = ds.getMinByMinAll(stockId, exstock);
		}else if(type.equals("hours")){
			btc_deal_list_vo = ds.getHoursByHoursAll(stockId, exstock);
		}else if(type.equals("day")){
			btc_deal_list_vo = ds.getDayByDayAll(stockId, exstock);
		}
		String data = "[";
		if(btc_deal_list_vo!=null){
			for(int i=0;i<btc_deal_list_vo.size();i++){
				Btc_deal_list_vo btc_deal_list = btc_deal_list_vo.get(i);
				data = data + "["+btc_deal_list.getBtc_deal_time()+","+btc_deal_list.getBtc_deal_RateOpen()+","+btc_deal_list.getBtc_deal_RateMax()+","+
				btc_deal_list.getBtc_deal_RateMin()+","+btc_deal_list.getBtc_deal_RateClose()+","+btc_deal_list.getBtc_deal_Total()+"],";
			}
		}else{
			data = data + "[1394309060000,0,0,0,0,0],";
		}
		data = data + "]";
		System.out.println("已处理一次");
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml; charset=UTF-8");// 设置输出信息的格式及字符集
		response.setHeader("Cache-Control", "no-cache");
		out.println(data);
		out.close();
	}

}
