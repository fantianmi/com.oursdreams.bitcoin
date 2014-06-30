package com.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_user;

@Service
public class UserService {
	@Autowired
	private EntityDao entityDao;

	@Transactional
	public boolean vertify(String uusername, String upassword) {
		List<Object> list = entityDao.createQuery("select u from Btc_user u where u.uusername='" + uusername + "'");
		Btc_user user = new Btc_user();
		if (list.size() != 0) {
			user = (Btc_user) list.get(0);
		}
		return upassword.equals(user.getUpassword());
	}
	public boolean checkEmailExist(String uemail) {
		boolean flag = false;
		List<Object> list = entityDao.createQuery("select u from Btc_user u where u.uemail='" + uemail + "'");
		if (list.size() != 0) {
			flag = true;
		}
		return flag;
	}
	
	public boolean checkPhoneExist(String uphone) {
		boolean flag = false;
		List<Object> list = entityDao.createQuery("select u from Btc_user u where u.uphone='" + uphone + "'");
		if (list.size() != 0) {
			flag = true;
		}
		return flag;
	}
	public boolean checkucertificationExist(String ucertification) {
		boolean flag = false;
		List<Object> list = entityDao.createQuery("select u from Btc_user u where u.ucertification='" + ucertification + "'");
		if (list.size() != 0) {
			flag = true;
		}
		return flag;
	}
	
	public Btc_user getByUsername(String uusername){
		Btc_user user = new Btc_user();
		List<Object> list = entityDao.createQuery("select u from Btc_user u where u.uusername='" + uusername + "'");
		if (list.size() != 0) {
			user = (Btc_user) list.get(0);
			return user;
		}else{
			return user=null;
		}	
	}
	
	public List<Object> getAllUser(){
		List<Object> list = entityDao.createQuery("select btc_user from Btc_user btc_user order by btc_user.usdtime DESC");
		if (list.size() != 0) {
			return list;
		}else{
			return list=null;
		}	
	}
	
	public int countAllUser(){
		int num=0;
		List<Object> list = entityDao.createQuery("select count(btc_user) from Btc_user btc_user");
		if (list.size() != 0) {
			num = Integer.parseInt(list.get(0).toString());
			return num;
		}else{
			return num;
		}	
	}
	
	public Btc_user getByUid(int uid){
		Btc_user user = new Btc_user();
		List<Object> list = entityDao.createQuery("select u from Btc_user u where u.uid='" + uid + "'");
		if (list.size() != 0) {
			user = (Btc_user) list.get(0);
			return user;
		}else{
			return user=null;
		}	
	}
	
	public List<Object> getByInviteName(String invite){
		List<Object> list = entityDao.createQuery("select u from Btc_user u where u.uinvite_username='" + invite + "'");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}

	public void register_step1(Btc_user user) {
		entityDao.save(user);
	}
	
	public void register_step2(Btc_user user) {
		entityDao.update(user);
	}
	
	public void updateUser(Btc_user user) {
		entityDao.update(user);
	}
	
	public void deleteUser(Btc_user user) {
		entityDao.delete(user);
	}
	
	public Btc_user getByUserId(int uid){
		Btc_user user = new Btc_user();
		List<Object> list = entityDao.createQuery("select u from Btc_user u where u.uid='" + uid + "'");
		if (list.size() != 0) {
			user = (Btc_user) list.get(0);
			return user;
		}else{
			return user=null;
		}	
	}
	
	/**
	 * count user invite amount
	 */
	public int countUInvite(String username){
		int amount=0;
		List<Object> list = entityDao.createQuery("select count(u) from Btc_user u where u.uinvite_username='"+username+"' and u.rengouget!=null");
		if(list.size()!=0){
			if(list.get(0)!=null){
				amount = Integer.parseInt(list.get(0).toString());
			}
		}
		return amount;
	}
	
	public Map<Integer,Btc_user> getUserMap(){
		Map<Integer,Btc_user> usermap = new HashMap<Integer,Btc_user>();
		Btc_user user = new Btc_user();
		List<Object> list = entityDao.createQuery("select btc_user from Btc_user btc_user");
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				user = (Btc_user)list.get(i);
				usermap.put(user.getUid(), user);
			}
			return usermap;
		}else{
			return null;
		}	
	}
	
	public Map<String,Btc_user> getUserMap2(){
		Map<String,Btc_user> usermap = new HashMap<String,Btc_user>();
		Btc_user user = new Btc_user();
		List<Object> list = entityDao.createQuery("select btc_user from Btc_user btc_user");
		if (list.size() != 0) {
			for(int i=0;i<list.size();i++){
				user = (Btc_user)list.get(i);
				usermap.put(user.getUusername(), user);
			}
			return usermap;
		}else{
			return null;
		}	
	}
}
