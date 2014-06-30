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
@Table(name = "btc_sellBTC_order")
public class Btc_sellBTC_order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bso_btc_id", nullable = false)
	private Integer bso_btc_id;
	@Column(name = "bso_btc_sellRate")
	private BigDecimal bso_btc_sellRate;
	@Column(name = "bso_btc_sellQuantity")
	private BigDecimal bso_btc_sellQuantity;
	@Column(name="bso_btc_exchange")
	private BigDecimal bso_btc_exchange;
	@Column(name="bso_btc_poundage")
	private BigDecimal bso_btc_poundage;
	@Column(name="bso_btc_sell_time")
	private String bso_btc_sell_time;
	@Column(name="bso_btc_sell_success_time")
	private String bso_btc_sell_success_time;
	@Column(name="bso_btc_remark")
	private String bso_btc_remark;
	@Column(name="bso_btc_state")
	private int bso_btc_state;
	@Column(name="bso_btc_sell_state")
	private int bso_btc_sell_state;
	@Column(name="uid")
	private int uid;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Integer getBso_btc_id() {
		return bso_btc_id;
	}

	public void setBso_btc_id(Integer bso_btc_id) {
		this.bso_btc_id = bso_btc_id;
	}

	public BigDecimal getBso_btc_sellRate() {
		return bso_btc_sellRate;
	}

	public void setBso_btc_sellRate(BigDecimal bso_btc_sellRate) {
		this.bso_btc_sellRate = bso_btc_sellRate;
	}

	public BigDecimal getBso_btc_sellQuantity() {
		return bso_btc_sellQuantity;
	}

	public void setBso_btc_sellQuantity(BigDecimal bso_btc_sellQuantity) {
		this.bso_btc_sellQuantity = bso_btc_sellQuantity;
	}

	public BigDecimal getBso_btc_exchange() {
		return bso_btc_exchange;
	}

	public void setBso_btc_exchange(BigDecimal bso_btc_exchange) {
		this.bso_btc_exchange = bso_btc_exchange;
	}

	public BigDecimal getBso_btc_poundage() {
		return bso_btc_poundage;
	}

	public void setBso_btc_poundage(BigDecimal bso_btc_poundage) {
		this.bso_btc_poundage = bso_btc_poundage;
	}

	public String getBso_btc_sell_time() {
		return bso_btc_sell_time;
	}

	public void setBso_btc_sell_time(String bso_btc_sell_time) {
		this.bso_btc_sell_time = bso_btc_sell_time;
	}

	public String getBso_btc_sell_sucess_time() {
		return bso_btc_sell_success_time;
	}

	public void setBso_btc_sell_sucess_time(String bso_btc_sell_sucess_time) {
		this.bso_btc_sell_success_time = bso_btc_sell_sucess_time;
	}

	public String getBso_btc_remark() {
		return bso_btc_remark;
	}

	public void setBso_btc_remark(String bso_btc_remark) {
		this.bso_btc_remark = bso_btc_remark;
	}

	public int getBso_btc_state() {
		return bso_btc_state;
	}

	public void setBso_btc_state(int bso_btc_state) {
		this.bso_btc_state = bso_btc_state;
	}

	public int getBso_btc_sell_state() {
		return bso_btc_sell_state;
	}

	public void setBso_btc_sell_state(int bso_btc_sell_state) {
		this.bso_btc_sell_state = bso_btc_sell_state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
