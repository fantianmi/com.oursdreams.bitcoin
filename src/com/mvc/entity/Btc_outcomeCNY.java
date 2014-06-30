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
@Table(name = "btc_outcomeCNY")
public class Btc_outcomeCNY implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_outcomeCNY_id", nullable = false)
	private Integer btc_outcomeCNY_id;
	@Column(name = "btc_outcomeCNY_amount")
	private BigDecimal btc_outcomeCNY_amount;
	@Column(name = "btc_outcomeCNY_reason")
	private String btc_outcomeCNY_reason;
	@Column(name = "btc_outcomeCNY_time")
	private String btc_outcomeCNY_time;

	public String getBtc_outcomeCNY_time() {
		return btc_outcomeCNY_time;
	}

	public void setBtc_outcomeCNY_time(String btc_outcomeCNY_time) {
		this.btc_outcomeCNY_time = btc_outcomeCNY_time;
	}

	public Integer getBtc_outcomeCNY_id() {
		return btc_outcomeCNY_id;
	}

	public void setBtc_outcomeCNY_id(Integer btc_outcomeCNY_id) {
		this.btc_outcomeCNY_id = btc_outcomeCNY_id;
	}

	public BigDecimal getBtc_outcomeCNY_amount() {
		return btc_outcomeCNY_amount;
	}

	public void setBtc_outcomeCNY_amount(BigDecimal btc_outcomeCNY_amount) {
		this.btc_outcomeCNY_amount = btc_outcomeCNY_amount;
	}

	public String getBtc_outcomeCNY_reason() {
		return btc_outcomeCNY_reason;
	}

	public void setBtc_outcomeCNY_reason(String btc_outcomeCNY_reason) {
		this.btc_outcomeCNY_reason = btc_outcomeCNY_reason;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
