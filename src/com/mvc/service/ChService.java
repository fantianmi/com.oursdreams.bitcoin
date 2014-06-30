package com.mvc.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mvc.dao.EntityDao;
import com.mvc.entity.Chstatus;

@Service
public class ChService {
	@Autowired
	private EntityDao entityDao;
	
	
	@Transactional
	public Chstatus getStatusByUid(int cid) {
		Chstatus status = new Chstatus();
		List<Object> list = entityDao.createQuery("SELECT status from Chstatus status where status.cid='"+cid+"'");
		if (list.size() != 0) {
			status = (Chstatus)list.get(0);
			return status;
		}else{
			return null;
		}	
	}
	
	public void updateChstatus(Chstatus status) {
		entityDao.update(status);
	}
	
}
