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
@Table(name = "btc_mail_config")
public class Btc_mail_config implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_mail_id", nullable = false)
	private Integer btc_mail_id;
	@Column(name = "btc_mail_username")
	private String btc_mail_username;
	@Column(name = "btc_mail_password")
	private String btc_mail_password;
	@Column(name="btc_mail_hostName")
	private String btc_mail_hostName;
	@Column(name="btc_mail_adr")
	private String btc_mail_adr;
	@Column(name="btc_mail_pop_adr")
	private String btc_mail_pop_adr;
	@Column(name="btc_mail_smtp_adr")
	private String btc_mail_smtp_adr;
	@Column(name="btc_mail_imap_adr")
	private String btc_mail_imap_adr;
	@Column(name="btc_wangzhi")
	private String btc_wangzhi;

	public String getBtc_wangzhi() {
		return btc_wangzhi;
	}

	public void setBtc_wangzhi(String btc_wangzhi) {
		this.btc_wangzhi = btc_wangzhi;
	}

	public Integer getBtc_mail_id() {
		return btc_mail_id;
	}

	public void setBtc_mail_id(Integer btc_mail_id) {
		this.btc_mail_id = btc_mail_id;
	}


	public String getBtc_mail_username() {
		return btc_mail_username;
	}

	public void setBtc_mail_username(String btc_mail_username) {
		this.btc_mail_username = btc_mail_username;
	}

	public String getBtc_mail_password() {
		return btc_mail_password;
	}

	public void setBtc_mail_password(String btc_mail_password) {
		this.btc_mail_password = btc_mail_password;
	}

	public String getBtc_mail_hostName() {
		return btc_mail_hostName;
	}

	public void setBtc_mail_hostName(String btc_mail_hostName) {
		this.btc_mail_hostName = btc_mail_hostName;
	}

	public String getBtc_mail_adr() {
		return btc_mail_adr;
	}

	public void setBtc_mail_adr(String btc_mail_adr) {
		this.btc_mail_adr = btc_mail_adr;
	}

	public String getBtc_mail_pop_adr() {
		return btc_mail_pop_adr;
	}

	public void setBtc_mail_pop_adr(String btc_mail_pop_adr) {
		this.btc_mail_pop_adr = btc_mail_pop_adr;
	}

	public String getBtc_mail_smtp_adr() {
		return btc_mail_smtp_adr;
	}

	public void setBtc_mail_smtp_adr(String btc_mail_smtp_adr) {
		this.btc_mail_smtp_adr = btc_mail_smtp_adr;
	}

	public String getBtc_mail_imap_adr() {
		return btc_mail_imap_adr;
	}

	public void setBtc_mail_imap_adr(String btc_mail_imap_adr) {
		this.btc_mail_imap_adr = btc_mail_imap_adr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
