package com.mvc.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_content;
/**
 * 处理用户账户帐本的增删改查
 * @author jack
 *
 */
@Service
public class CotentService {
	@Autowired
	private EntityDao entityDao;
	
	@Transactional
	public Btc_content getContentByid(int btc_content_id){
		Btc_content bc = new Btc_content();
		List<Object> list = entityDao.createQuery("select bc from Btc_content bc where bc.btc_content_id='" + btc_content_id + "'");
		if (list.size() != 0) {
			bc = (Btc_content)list.get(0);
			return bc;
		}else{
			return bc = null;
		}	
	}
	
	public List<Object> getAllContent(){
		List<Object> list = entityDao.createQuery("select bc from Btc_content bc where bc.btc_content_type='公告' order by bc.btc_content_time desc and bc.btc_content_type desc");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public List<Object> getNewsLimit(){
		List<Object> list = entityDao.createQuery("select bc from Btc_content bc where bc.btc_content_type='公告' order by bc.btc_content_time desc and bc.btc_content_type desc",0,6);
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	public List<Object> getNewsCAll(){
		List<Object> list = entityDao.createQuery("select bc from Btc_content bc where bc.btc_content_type='公告' order by bc.btc_content_time desc and bc.btc_content_type desc");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public List<Object> getNewsAll(){
		List<Object> list = entityDao.createQuery("select bc from Btc_content bc order by bc.btc_content_time desc and bc.btc_content_type desc");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public Map<String,Object> getIndexContent(int stockid){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> list = entityDao.createQuery("select bc from Btc_content bc where bc.btc_content_type='首页关于我们' order by bc.btc_content_time desc and bc.btc_content_type desc");
		Btc_content bc = new Btc_content();
		if (list.size() != 0) {
			bc = (Btc_content)list.get(0);
			map.put(bc.getBtc_content_type(), bc);
		}
		list = entityDao.createQuery("select bc from Btc_content bc where bc.btc_content_stock_id='"+stockid+"' and bc.btc_content_type='币种介绍' order by bc.btc_content_time desc and bc.btc_content_type desc");
		if (list.size()!=0){
			bc = (Btc_content)list.get(0);
			map.put(bc.getBtc_content_type(), bc);
		}
		list = entityDao.createQuery("select bc from Btc_content bc where bc.btc_content_type='客服帮助电话' order by bc.btc_content_time desc and bc.btc_content_type desc");
		if(list.size()!=0){
			bc = (Btc_content)list.get(0);
			map.put(bc.getBtc_content_type(), bc);
		}
		list = entityDao.createQuery("select bc from Btc_content bc where bc.btc_content_type='备案信息等' order by bc.btc_content_time desc and bc.btc_content_type desc");
		if(list.size()!=0){
			bc = (Btc_content)list.get(0);
			map.put(bc.getBtc_content_type(), bc);
		}
		list = entityDao.createQuery("select bc from Btc_content bc where bc.btc_content_type='网站标题' order by bc.btc_content_time desc and bc.btc_content_type desc");
		if(list.size()!=0){
			bc = (Btc_content)list.get(0);
			map.put(bc.getBtc_content_type(), bc);
		}
		return map;
	}
	
	/**
	 * 更新用户账户本
	 * @param bab 传入账户本对象
	 */
	public void updateContent(Btc_content bc) {
		entityDao.update(bc);
	}
	public void deleteContent(Btc_content bc) {
		entityDao.delete(bc);
	}
	public void saveContent(Btc_content bc) {
		entityDao.save(bc);
	}
}
