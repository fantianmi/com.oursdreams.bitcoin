package com.mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.entity.Btc_user;
import com.mvc.entity.Btc_votehistory;
import com.mvc.entity.Btc_votestock;
import com.mvc.service.VoteHistoryService;
import com.mvc.service.VoteStockService;
@Controller
@RequestMapping("/votestock.htm")
public class VoteStockController {
	@Autowired
	private VoteStockService vss = new VoteStockService();
	@Autowired
	private VoteHistoryService vhs = new VoteHistoryService();

	protected final transient Log log = LogFactory.getLog(VoteStockController.class);

	/**
	 * 生成订单，并从用户帐本中扣除相应费用
	 * 
	 * @param modelmap
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params="add")
	public String createbank(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		String vstockname = request.getParameter("vstockname").toString();
		String vstockEngname = request.getParameter("vstockEngname").toString();
		String vstockfullName = request.getParameter("vstockfullName").toString();
		
		Btc_votestock bvs = new Btc_votestock();
		bvs.setVstockname(vstockname);
		bvs.setVstockEngname(vstockEngname);
		bvs.setVstockfullName(vstockfullName);
		bvs.setUsername(user.getUusername());
		bvs.setVstatus("等待审核");
		bvs.setVamount(0);
		vss.saveBtc_votestock(bvs);
		
		request.setAttribute("msg", "添加成功,等待管理员审核！");
		request.setAttribute("href", "index.htm?votestock");
		return "votestock";
	}
	
	
	@RequestMapping(params="votestock")
	public String votestock(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("globaluser") == null) {
			request.setAttribute("msg", "登陆后才能进行此操作！");
			request.setAttribute("href", "index.htm");
			return "login";
		}
		Btc_user user = (Btc_user)session.getAttribute("globaluser");
		int uid = user.getUid();
		Btc_votestock bvs = new Btc_votestock();
		Btc_votehistory bvhs = new Btc_votehistory();
		String vids[] = request.getParameterValues("checkbox");
		String msg = "恭喜您投票成功";
		int vid = 0;
		if (vids != null) {
			for (int i = 0; i < vids.length; i++) {
				vid = Integer.parseInt(vids[i]);
				if(vhs.getHistroyByUidAndVid(uid, vid)!=null){
					request.setAttribute("msg", "非法操作");
					request.setAttribute("href", "back");
					return "votestock";
				}
				bvs = vss.getVoteStockByVid(vid);
				bvs.setVamount(bvs.getVamount()+1);
				vss.updateBtc_votestock(bvs);
				bvhs.setVh_uid(uid);
				bvhs.setVh_vid(vid);
				vhs.saveBtc_votehistory(bvhs);
				
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("href", "index.htm?votestock");
		return "votestock";
	}
}
