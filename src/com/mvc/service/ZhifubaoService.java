package com.mvc.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_zhifubao;

@Service
public class ZhifubaoService {
	@Autowired
	private EntityDao entityDao;
	
	
	@Transactional
	public List<Object> getZhifubaoByUid(int uid) {
		List<Object> list = entityDao.createQuery("SELECT zhi from Btc_zhifubao zhi where zhi.uid='"+uid+"'");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public List<Object> getZhifubaoByStatus(String status) {
		List<Object> list = entityDao.createQuery("SELECT zhi from Btc_zhifubao zhi where zhi.status='"+status+"'");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public Btc_zhifubao getzhifubaoId(int id) {
		Btc_zhifubao zhi = new Btc_zhifubao();
		List<Object> list = entityDao.createQuery("SELECT zhi from Btc_zhifubao zhi where zhi.id='"+id+"'");
		if (list.size() != 0) {
			zhi = (Btc_zhifubao)list.get(0);
			return zhi;
		}else{
			return null;
		}	
	}
	
	public List<Object> getZhifubaoByStatusandUid(String status,int uid) {
		List<Object> list = entityDao.createQuery("SELECT zhi from Btc_zhifubao zhi where zhi.status='"+status+"' and zhi.uid='"+uid+"'");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public void saveZhi(Btc_zhifubao zhi) {
		entityDao.save(zhi);
	}
	
	public void updateZhi(Btc_zhifubao zhi) {
		entityDao.update(zhi);
	}
	
	public void deleteZhi(Btc_zhifubao zhi) {
		entityDao.delete(zhi);
	}
}
