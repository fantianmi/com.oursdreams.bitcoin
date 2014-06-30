package com.mvc.service.game;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mvc.dao.EntityDao;
import com.mvc.entity.games.Games_luckwheel;

@Service
public class LuckWheelService {
	@Autowired
	private EntityDao entityDao;
	@Transactional
	public Integer CountLuckWheelUser(String type) {
		List<Object> list = entityDao.createQuery("SELECT lw from Games_luckwheel lw where lw.type='"+type+"' group by lw.uid");
		if (list.size() != 0) {
			return list.size();
		}
		return 0;	
	}
	
	public Integer CountPlays(String type) {
		List<Object> list = entityDao.createQuery("SELECT count(lw.id) from Games_luckwheel lw where lw.type='"+type+"'");
		if (list.size() != 0) {
			if(list.get(0)!=null){
				return Integer.parseInt(list.get(0).toString());
			}
		}
		return 0;	
	}
	
	public List<Object> getMyGameLog(int uid,int start,int count) {
		List<Object> list = entityDao.createQuery("SELECT lw from Games_luckwheel lw where lw.uid="+uid+"",start,count);
		if (list.size() != 0) {
			return list;
		}
		return null;	
	}
	
	public  List<Object> getLuckWheel(String type) {
		List<Object> list = entityDao.createQuery("SELECT lw from Games_luckwheel lw where lw.type='"+type+"'");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public List<Object> getLuckWheel(String type,int start,int count) {
		List<Object> list = entityDao.createQuery("SELECT lw from Games_luckwheel lw where lw.type='"+type+"'",start,count);
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	public void saveLuckWheel(Games_luckwheel lw) {
		entityDao.save(lw);
	}
	public void updateLuckWheel(Games_luckwheel lw) {
		entityDao.update(lw);
	}
	
	public void deleteLuckWheel(Games_luckwheel lw) {
		entityDao.delete(lw);
	}
}
