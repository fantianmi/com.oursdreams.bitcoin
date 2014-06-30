package com.mvc.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "btc_outcomeStock")
public class Btc_outcomeStock implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_outcomeStock_id", nullable = false)
	private Integer btc_outcomeStock_id;
	@Column(name = "btc_outcomeStock_name")
	private String btc_outcomeStock_name;
	@Column(name = "btc_outcomeStock_amount")
	private BigDecimal btc_outcomeStock_amount;
	@Column(name="btc_outcomeStock_reason")
	private String btc_outcomeStock_reason;
	@Column(name="btc_outcomeStock_time")
	private String btc_outcomeStock_time;


	public String getBtc_outcomeStock_time() {
		return btc_outcomeStock_time;
	}


	public void setBtc_outcomeStock_time(String btc_outcomeStock_time) {
		this.btc_outcomeStock_time = btc_outcomeStock_time;
	}


	public Integer getBtc_outcomeStock_id() {
		return btc_outcomeStock_id;
	}


	public void setBtc_outcomeStock_id(Integer btc_outcomeStock_id) {
		this.btc_outcomeStock_id = btc_outcomeStock_id;
	}


	public String getBtc_outcomeStock_name() {
		return btc_outcomeStock_name;
	}


	public void setBtc_outcomeStock_name(String btc_outcomeStock_name) {
		this.btc_outcomeStock_name = btc_outcomeStock_name;
	}


	public BigDecimal getBtc_outcomeStock_amount() {
		return btc_outcomeStock_amount;
	}


	public void setBtc_outcomeStock_amount(BigDecimal btc_outcomeStock_amount) {
		this.btc_outcomeStock_amount = btc_outcomeStock_amount;
	}


	public String getBtc_outcomeStock_reason() {
		return btc_outcomeStock_reason;
	}


	public void setBtc_outcomeStock_reason(String btc_outcomeStock_reason) {
		this.btc_outcomeStock_reason = btc_outcomeStock_reason;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
