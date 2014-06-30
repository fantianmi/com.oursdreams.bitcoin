package com.mvc.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "btc_mail_content")
public class Btc_mail_content implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_mail_content_id", nullable = false)
	private Integer btc_mail_content_id;
	@Column(name = "btc_mail_content_body")
	private String btc_mail_content_body;
	@Column(name = "btc_mail_content_subject")
	private String btc_mail_content_subject;
	@Column(name="btc_mail_content_use")
	private String btc_mail_content_use;



	public Integer getBtc_mail_content_id() {
		return btc_mail_content_id;
	}



	public void setBtc_mail_content_id(Integer btc_mail_content_id) {
		this.btc_mail_content_id = btc_mail_content_id;
	}



	public String getBtc_mail_content_body() {
		return btc_mail_content_body;
	}



	public void setBtc_mail_content_body(String btc_mail_content_body) {
		this.btc_mail_content_body = btc_mail_content_body;
	}



	public String getBtc_mail_content_subject() {
		return btc_mail_content_subject;
	}



	public void setBtc_mail_content_subject(String btc_mail_content_subject) {
		this.btc_mail_content_subject = btc_mail_content_subject;
	}



	public String getBtc_mail_content_use() {
		return btc_mail_content_use;
	}



	public void setBtc_mail_content_use(String btc_mail_content_use) {
		this.btc_mail_content_use = btc_mail_content_use;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
