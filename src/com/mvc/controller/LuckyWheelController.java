package com.mvc.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mvc.entity.Btc_account_book;
import com.mvc.entity.Btc_holding;
import com.mvc.entity.Btc_user;
import com.mvc.entity.games.Games_luckwheel;
import com.mvc.service.AccountService;
import com.mvc.service.HoldingService;
import com.mvc.service.OrderService;
import com.mvc.service.StockService;
import com.mvc.service.game.LuckWheelService;
import com.mvc.util.DataUtil;
import com.mvc.util.HoldingUtil;
import com.mvc.util.JsonUtils;
@Controller
@RequestMapping("/luckwheel.htm")
public class LuckyWheelController {
	@Autowired
	private LuckWheelService lws;
	@Autowired
	private HoldingService holds;
	@Autowired
	private StockService stocks;
	@Autowired
	private OrderService orders;
	@Autowired
	private AccountService acounts;
	@Autowired
	private DataUtil datautil;
	@Autowired
	private HoldingUtil holdutil;
	
	
	protected final transient Log log = LogFactory.getLog(LuckyWheelController.class);

	@RequestMapping
	public String load(HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "index";
		}
		/**
		 * prepare data
		 */
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		Btc_account_book abook = acounts.getByUidForAcount(user.getUid());
		if (abook == null) {
			session.setAttribute("ab_cny", "0.00");
		} else {
			BigDecimal ab_cny_show = abook.getAb_cny().setScale(2,
					BigDecimal.ROUND_HALF_UP);
			session.setAttribute("ab_cny", ab_cny_show);
		}
		Map<Integer, Object> stock_map = stocks.getBtc_stock();
		session.setAttribute("stock_map", stock_map);
		
		if (holds.getBtc_holding(user.getUid()) != null) {
			List<Object> btc_holding_list = holds.getBtc_holding(user
					.getUid());
			session.setAttribute("btc_holding_list", btc_holding_list);
			Map<Integer,Object> btc_holding_map = holds.getBtc_holdingToMapByUid(user
					.getUid());
			session.setAttribute("btc_holding_map", btc_holding_map);
		} else {
			session.setAttribute("btc_holding_list", null);
			session.setAttribute("btc_holding_map", null);
		}
		Map<Integer, Object> allstockmap = stocks.getBtc_stockByState(1);
		Map<Integer, Object> userholdmap = holds.getBtc_holdingToMapByUid(user.getUid());
		session.setAttribute("allstockmap", allstockmap);
		session.setAttribute("userholdmap", userholdmap);
		return "luckwheel";
	}
	
	@RequestMapping(params="luckwheel")
	public String luckwheel(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("zhushu")BigDecimal zhushu,
			@RequestParam("stockid")int stockid
			) throws IOException {
		ResourceBundle res = ResourceBundle.getBundle("luckwheel");
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			JsonUtils.string2json("{\"success\":\"false\",\"msg\":\"请先登录\"}");
			return null;
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		Btc_holding hold = holds.getBtc_holding(user.getUid(), stockid);
		if(holds.getBtc_holding(user.getUid(), stockid)==null){
			JsonUtils.string2json("{\"success\":\"false\",\"msg\":\"您的余额不足，请先充值\"}");
			return null;
		}
		if(zhushu.compareTo(hold.getBtc_stock_amount())>0){
		   JsonUtils.string2json("{\"success\":\"false\",\"msg\":\"您的余额不足，请重新输入下注\"}");
		   return null;
		}
		
		try {
            Object obj[] = lottery();
            /**
             * db operator
             */
//            final int angle = ((Integer) obj[0]).intValue();
//            final int awards = ((Integer) obj[1]).intValue();
            final String msg = obj[2].toString();
            BigDecimal beishu = new BigDecimal(res.getString(msg));
            Games_luckwheel lw = new Games_luckwheel();
            //user can get award
            BigDecimal gawards = new BigDecimal(0);
            gawards = zhushu.multiply(beishu);
            
            lw.setAmount(zhushu);
            lw.setAwards(gawards);
            lw.setTimes(datautil.getTimeNow("second"));
            lw.setStatus("已累加到您的账户中");
            lw.setType("luckwheel");
            lw.setUid(user.getUid());
            lw.setMsg(msg);
            lw.setStockid(stockid);
            lws.saveLuckWheel(lw);
            
            holdutil.addStock(user.getUid(), stockid, gawards.subtract(zhushu));
            
            response.getWriter().write(JsonUtils.object2json(obj));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            JsonUtils.string2json("{\"success\":\"false\",\"msg\":\"操作失败\"}");
        }
        return null;
	}
	
	
	// 抽奖并返回角度和奖项
    public Object[] lottery() {
        Object[][] lott = getLunckArray();
        // 概率数组
        Integer obj[] = new Integer[lott.length];
        for (int i = 0; i < lott.length; i++) {
            obj[i] = (Integer) lott[i][4];
        }
        Integer prizeId = getRand(obj); // 根据概率获取奖项id
        // 旋转角度
        int angle = new Random().nextInt((Integer) lott[prizeId][2] - (Integer) lott[prizeId][1])
                + (Integer) lott[prizeId][1];
        String msg = (String) lott[prizeId][3];// 提示信息
        return new Object[] { angle, prizeId, msg };
    }

    protected static Object[][] getLunckArray() {
        Object luckArr[][] = new Object[][] { { 1, 1, 29, "一等奖", 1 }, { 2, 302, 328, "二等奖", 2 },
                { 3, 242, 268, "三等奖", 5 }, { 4, 182, 208, "四等奖", 7 }, { 5, 122, 148, "五等奖", 10 },
                { 6, 62, 88, "六等奖", 25 }, { 7, 32, 58, "七等奖", 8 }, { 7, 92, 118, "七等奖", 8 }, { 7, 212, 238, "七等奖", 8 },
                { 7, 152, 212, "七等奖", 8 }, { 7, 272, 298, "七等奖", 9 }, { 7, 332, 358, "七等奖", 9 } };
        return luckArr;
    }

    protected static Integer getRand(Integer obj[]) {
        Integer result = null;
        int sum = 0;// 概率数组的总概率精度
        for (int i = 0; i < obj.length; i++) {
            sum += obj[i];
        }
        for (int i = 0; i < obj.length; i++) {
            int randomNum = new Random().nextInt(sum);// 随即生成1到sum的整数
            if (randomNum < obj[i]) {// 中奖
                result = i;
                break;
            } else {
                sum -= obj[i];
            }
        }
        return result;
    }
    protected void saveLunckWheel(Object[] obj) {
    }
}
