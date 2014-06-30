package com.mvc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_votestock;
/**
 * 处理用户账户帐本的增删改查
 * @author jack
 *
 */
@Service
public class VoteStockService {
	@Autowired
	private EntityDao entityDao;
	
	/**
	 * 根据用户id查询对应账本信息
	 * @param uid
	 * @return
	 */
	public Btc_votestock getVoteStockByVid(int vid){
		Btc_votestock bvs = new Btc_votestock();
		List<Object> list = entityDao.createQuery("select bvs from Btc_votestock bvs where bvs.vid='" + vid + "'");
		if (list.size() != 0) {
			bvs = (Btc_votestock)list.get(0);
			return bvs;
		}else{
			return null;
		}	
	}
	
	public List<Object> getVoteStockAll(){
		List<Object> list = entityDao.createQuery("select bvs from Btc_votestock bvs");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public List<Object> getVoteStockAllByStatus(String status){
		List<Object> list = entityDao.createQuery("select bvs from Btc_votestock bvs where bvs.vstatus='"+status+"' order by bvs.vamount desc");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public void updateBtc_votestock(Btc_votestock bvs) {
		entityDao.update(bvs);
	}
	public void saveBtc_votestock(Btc_votestock bvs) {
		entityDao.save(bvs);
	}
	public void deleteBtc_votestock(Btc_votestock bvs) {
		entityDao.delete(bvs);
	}
}
