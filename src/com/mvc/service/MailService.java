package com.mvc.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mvc.dao.EntityDao;
import com.mvc.entity.Btc_mail_config;
import com.mvc.entity.Btc_mail_content;
/**
 * 处理用户账户帐本的增删改查
 * @author jack
 *
 */
@Service
public class MailService {
	@Autowired
	private EntityDao entityDao;
	
	/**
	 * 根据用户id查询对应账本信息
	 * @param uid
	 * @return
	 */
	public Btc_mail_config getMailConfig(){
		Btc_mail_config config = new Btc_mail_config();
		List<Object> list = entityDao.createQuery("select bmc from Btc_mail_config bmc");
		if (list.size() != 0) {
			config = (Btc_mail_config)list.get(0);
			return config;
		}else{
			return null;
		}	
	}
	
	
	
	/**
	 * 更新用户账户本
	 * @param bab 传入账户本对象
	 */
	public void updateMailConfig(Btc_mail_config config) {
		entityDao.update(config);
	}
	//mail content **************************************************************************************************
	public Btc_mail_content getMailContent(String useway){
		Btc_mail_content content = new Btc_mail_content();
		List<Object> list = entityDao.createQuery("select bmc from Btc_mail_content bmc where bmc.btc_mail_content_use='"+useway+"'");
		if (list.size() != 0) {
			content = (Btc_mail_content)list.get(0);
			return content;
		}else{
			return null;
		}	
	}
	
	public List<Object> getMailContentAll(){
		List<Object> list = entityDao.createQuery("select bmc from Btc_mail_content bmc");
		if (list.size() != 0) {
			return list;
		}else{
			return null;
		}	
	}
	
	public void saveMailContent(Btc_mail_content content) {
		entityDao.save(content);
	}
	public void updateMailContent(Btc_mail_content content) {
		entityDao.update(content);
	}
	public void deleteMailContent(Btc_mail_content content) {
		entityDao.delete(content);
	}
}
