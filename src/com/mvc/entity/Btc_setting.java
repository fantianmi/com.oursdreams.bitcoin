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
@Table(name = "btc_setting")
public class Btc_setting implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_setting_id", nullable = false)
	private Integer btc_setting_id;
	@Column(name="btc_setting_pocket_secret")
	private String btc_setting_pocket_secret;
	@Column(name="btc_setting_pocket_adr")
	private String btc_setting_pocket_adr;
	@Column(name="btc_setting_pocket_callbackUrl")
	private String btc_setting_pocket_callbackUrl;

	public Integer getBtc_setting_id() {
		return btc_setting_id;
	}

	public void setBtc_setting_id(Integer btc_setting_id) {
		this.btc_setting_id = btc_setting_id;
	}

	public String getBtc_setting_pocket_secret() {
		return btc_setting_pocket_secret;
	}

	public void setBtc_setting_pocket_secret(String btc_setting_pocket_secret) {
		this.btc_setting_pocket_secret = btc_setting_pocket_secret;
	}

	public String getBtc_setting_pocket_adr() {
		return btc_setting_pocket_adr;
	}

	public void setBtc_setting_pocket_adr(String btc_setting_pocket_adr) {
		this.btc_setting_pocket_adr = btc_setting_pocket_adr;
	}

	public String getBtc_setting_pocket_callbackUrl() {
		return btc_setting_pocket_callbackUrl;
	}

	public void setBtc_setting_pocket_callbackUrl(
			String btc_setting_pocket_callbackUrl) {
		this.btc_setting_pocket_callbackUrl = btc_setting_pocket_callbackUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
