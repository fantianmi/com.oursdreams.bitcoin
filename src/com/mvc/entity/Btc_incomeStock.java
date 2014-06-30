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
@Table(name = "btc_incomeStock")
public class Btc_incomeStock implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_incomeStock_id", nullable = false)
	private Integer btc_incomeStock_id;
	@Column(name = "btc_incomeStock_name")
	private String btc_incomeStock_name;
	@Column(name = "btc_incomeStock_amount")
	private BigDecimal btc_incomeStock_amount;
	@Column(name="btc_incomeStock_reason")
	private String btc_incomeStock_reason;
	@Column(name="btc_incomeStock_time")
	private String btc_incomeStock_time;

	public String getBtc_incomeStock_time() {
		return btc_incomeStock_time;
	}

	public void setBtc_incomeStock_time(String btc_incomeStock_time) {
		this.btc_incomeStock_time = btc_incomeStock_time;
	}

	public Integer getBtc_incomeStock_id() {
		return btc_incomeStock_id;
	}

	public void setBtc_incomeStock_id(Integer btc_incomeStock_id) {
		this.btc_incomeStock_id = btc_incomeStock_id;
	}

	public String getBtc_incomeStock_name() {
		return btc_incomeStock_name;
	}

	public void setBtc_incomeStock_name(String btc_incomeStock_name) {
		this.btc_incomeStock_name = btc_incomeStock_name;
	}

	public BigDecimal getBtc_incomeStock_amount() {
		return btc_incomeStock_amount;
	}

	public void setBtc_incomeStock_amount(BigDecimal btc_incomeStock_amount) {
		this.btc_incomeStock_amount = btc_incomeStock_amount;
	}

	public String getBtc_incomeStock_reason() {
		return btc_incomeStock_reason;
	}

	public void setBtc_incomeStock_reason(String btc_incomeStock_reason) {
		this.btc_incomeStock_reason = btc_incomeStock_reason;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
