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
@Table(name = "btc_incomeCNY")
public class Btc_incomeCNY implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_incomeCNY_id", nullable = false)
	private Integer btc_incomeCNY_id;
	@Column(name = "btc_incomeCNY_amount")
	private BigDecimal btc_incomeCNY_amount;
	@Column(name = "btc_incomeCNY_reason")
	private String btc_incomeCNY_reason;
	@Column(name = "btc_incomeCNY_time")
	private String btc_incomeCNY_time;


	public String getBtc_incomeCNY_time() {
		return btc_incomeCNY_time;
	}


	public void setBtc_incomeCNY_time(String btc_incomeCNY_time) {
		this.btc_incomeCNY_time = btc_incomeCNY_time;
	}


	public Integer getBtc_incomeCNY_id() {
		return btc_incomeCNY_id;
	}


	public void setBtc_incomeCNY_id(Integer btc_incomeCNY_id) {
		this.btc_incomeCNY_id = btc_incomeCNY_id;
	}


	public BigDecimal getBtc_incomeCNY_amount() {
		return btc_incomeCNY_amount;
	}


	public void setBtc_incomeCNY_amount(BigDecimal btc_incomeCNY_amount) {
		this.btc_incomeCNY_amount = btc_incomeCNY_amount;
	}


	public String getBtc_incomeCNY_reason() {
		return btc_incomeCNY_reason;
	}


	public void setBtc_incomeCNY_reason(String btc_incomeCNY_reason) {
		this.btc_incomeCNY_reason = btc_incomeCNY_reason;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
