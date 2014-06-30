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
@Table(name = "btc_rechargeBTC_order")
public class Btc_rechargeBTC_order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bro_btc_id", nullable = false)
	private Integer bro_btc_id;
	@Column(name = "bro_btc_buyingRate")
	private BigDecimal bro_btc_buyingRate;
	@Column(name = "bro_btc_buyQuantity")
	private BigDecimal bro_btc_buyQuantity;
	@Column(name="bro_btc_exchange")
	private BigDecimal bro_btc_exchange;
	@Column(name="bro_btc_poundage")
	private BigDecimal bro_btc_poundage;
	@Column(name="bro_btc_recharge_time")
	private String bro_btc_recharge_time;
	@Column(name="bro_btc_recharge_success_time")
	private String bro_btc_recharge_sucess_time;
	
	public String getBro_btc_recharge_sucess_time() {
		return bro_btc_recharge_sucess_time;
	}


	public void setBro_btc_recharge_sucess_time(String bro_btc_recharge_sucess_time) {
		this.bro_btc_recharge_sucess_time = bro_btc_recharge_sucess_time;
	}


	public int getBro_btc_pay_state() {
		return bro_btc_pay_state;
	}


	public void setBro_btc_pay_state(int bro_btc_pay_state) {
		this.bro_btc_pay_state = bro_btc_pay_state;
	}


	@Column(name="bro_btc_remark")
	private String bro_btc_remark;
	@Column(name="bro_btc_state")
	private int bro_btc_state;
	@Column(name="bro_btc_pay_state")
	private int bro_btc_pay_state;
	@Column(name="uid")
	private int uid;


	public Integer getBro_btc_id() {
		return bro_btc_id;
	}


	public void setBro_btc_id(Integer bro_btc_id) {
		this.bro_btc_id = bro_btc_id;
	}


	public BigDecimal getBro_btc_buyingRate() {
		return bro_btc_buyingRate;
	}


	public void setBro_btc_buyingRate(BigDecimal bro_btc_buyingRate) {
		this.bro_btc_buyingRate = bro_btc_buyingRate;
	}


	public BigDecimal getBro_btc_buyQuantity() {
		return bro_btc_buyQuantity;
	}


	public void setBro_btc_buyQuantity(BigDecimal bro_btc_buyQuantity) {
		this.bro_btc_buyQuantity = bro_btc_buyQuantity;
	}


	public BigDecimal getBro_btc_exchange() {
		return bro_btc_exchange;
	}


	public void setBro_btc_exchange(BigDecimal bro_btc_exchange) {
		this.bro_btc_exchange = bro_btc_exchange;
	}


	public BigDecimal getBro_btc_poundage() {
		return bro_btc_poundage;
	}


	public void setBro_btc_poundage(BigDecimal bro_btc_poundage) {
		this.bro_btc_poundage = bro_btc_poundage;
	}


	public String getBro_btc_recharge_time() {
		return bro_btc_recharge_time;
	}


	public void setBro_btc_recharge_time(String bro_btc_recharge_time) {
		this.bro_btc_recharge_time = bro_btc_recharge_time;
	}


	public String getBro_btc_remark() {
		return bro_btc_remark;
	}


	public void setBro_btc_remark(String bro_btc_remark) {
		this.bro_btc_remark = bro_btc_remark;
	}


	public int getBro_btc_state() {
		return bro_btc_state;
	}


	public void setBro_btc_state(int bro_btc_state) {
		this.bro_btc_state = bro_btc_state;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
