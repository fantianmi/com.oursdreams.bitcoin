package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_fh_order;

@Service
public class FhOrderService {
	@Autowired
	private EntityDao entityDao;

	@Transactional
	public List<Object> getByDeliverstatus(String status) {
		List<Object> list = entityDao.createQuery("select bfo from Btc_fh_order bfo where bfo.isdeliver=" + status + "");
		if (list.size() != 0) {
			return list;
		}
		return null;
	}
	public Btc_fh_order getById(int id) {
		Btc_fh_order bfo = new Btc_fh_order();
		List<Object> list = entityDao.createQuery("select bfo from Btc_fh_order bfo where bfo.fh_order_id=" + id + "");
		if (list.size() != 0) {
			bfo = (Btc_fh_order)list.get(0);
			return bfo;
		}
		return null;
	}
	
	public List<Object> getByUid(int uid) {
		List<Object> list = entityDao.createQuery("select bfo from Btc_fh_order bfo where bfo.uid=" + uid + "");
		if (list.size() != 0) {
			return list;
		}
		return null;
	}
	public List<Object> getByUid(int uid,String status) {
		List<Object> list = entityDao.createQuery("select bfo from Btc_fh_order bfo where bfo.uid=" + uid + " and bfo.isget='"+status+"'");
		if (list.size() != 0) {
			return list;
		}
		return null;
	}
	
	public List<Object> getByUidandDeliverstatus(int uid,String status) {
		List<Object> list = entityDao.createQuery("select bfo from Btc_fh_order bfo where bfo.uid=" + uid + " and bfo.isdeliver='"+status+"'");
		if (list.size() != 0) {
			return list;
		}
		return null;
	}
	
	public List<Object> getByGetstatus(String status) {
		List<Object> list = entityDao.createQuery("select bfo from Btc_fh_order bfo where bfo.isget=" + status + "");
		if (list.size() != 0) {
			return list;
		}
		return null;
	}
	
	public void saveFhOrder(Btc_fh_order bfo) {
		entityDao.save(bfo);
	}
	public void updateFhOrder(Btc_fh_order bfo) {
		entityDao.update(bfo);
	}
	public void deleteFhOrder(Btc_fh_order bfo) {
		entityDao.delete(bfo);
	}
	
}
