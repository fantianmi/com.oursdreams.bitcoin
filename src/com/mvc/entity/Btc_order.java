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
@Table(name = "btc_order")
public class Btc_order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_order_id", nullable = false)
	private Integer btc_order_id;
	@Column(name = "btc_order_type")
	private String btc_order_type;
	@Column(name = "btc_order_price")
	private BigDecimal btc_order_price;
	@Column(name="btc_stock_id")
	private Integer btc_stock_id;
	@Column(name="btc_exstock_name")
	private String btc_exstock_name;
	@Column(name="btc_order_amount")
	private BigDecimal btc_order_amount;
	@Column(name="btc_order_time")
	private String btc_order_time;
	@Column(name="btc_order_success_time")
	private String btc_order_success_time;
	@Column(name="btc_order_status")
	private Integer btc_order_status;
	@Column(name="uid")
	private Integer uid;
	@Column(name="lockstatus")
	private Integer lockstatus;


	public Integer getLockstatus() {
		return lockstatus;
	}

	public void setLockstatus(Integer lockstatus) {
		this.lockstatus = lockstatus;
	}

	public String getBtc_exstock_name() {
		return btc_exstock_name;
	}

	public void setBtc_exstock_name(String btc_exstock_name) {
		this.btc_exstock_name = btc_exstock_name;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public Integer getBtc_order_id() {
		return btc_order_id;
	}


	public void setBtc_order_id(Integer btc_order_id) {
		this.btc_order_id = btc_order_id;
	}


	public String getBtc_order_type() {
		return btc_order_type;
	}


	public void setBtc_order_type(String btc_order_type) {
		this.btc_order_type = btc_order_type;
	}


	public BigDecimal getBtc_order_price() {
		return btc_order_price;
	}


	public void setBtc_order_price(BigDecimal btc_order_price) {
		this.btc_order_price = btc_order_price;
	}


	public Integer getBtc_stock_id() {
		return btc_stock_id;
	}


	public void setBtc_stock_id(Integer btc_stock_id) {
		this.btc_stock_id = btc_stock_id;
	}


	public BigDecimal getBtc_order_amount() {
		return btc_order_amount;
	}


	public void setBtc_order_amount(BigDecimal btc_order_amount) {
		this.btc_order_amount = btc_order_amount;
	}


	public String getBtc_order_time() {
		return btc_order_time;
	}


	public void setBtc_order_time(String btc_order_time) {
		this.btc_order_time = btc_order_time;
	}


	public String getBtc_order_success_time() {
		return btc_order_success_time;
	}


	public void setBtc_order_success_time(String btc_order_success_time) {
		this.btc_order_success_time = btc_order_success_time;
	}


	public Integer getBtc_order_status() {
		return btc_order_status;
	}


	public void setBtc_order_status(Integer btc_order_status) {
		this.btc_order_status = btc_order_status;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
