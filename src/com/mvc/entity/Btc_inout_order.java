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
@Table(name = "btc_inout_order")
public class Btc_inout_order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_inout_order_id", nullable = false)
	private Integer btc_inout_order_id;
	@Column(name = "btc_stock_id")
	private Integer btc_stock_id;
	@Column(name = "uid")
	private Integer uid;
	@Column(name = "btc_inout_adr")
	private String btc_inout_adr;
	@Column(name = "btc_inout_amount")
	private BigDecimal btc_inout_amount;
	@Column(name = "btc_inout_poundage")
	private BigDecimal btc_inout_poundage;
	@Column(name = "btc_inout_status")
	private String btc_inout_status;
	@Column(name = "btc_inout_msg")
	private String btc_inout_msg;
	@Column(name = "btc_inout_time")
	private String btc_inout_time;
	@Column(name = "btc_inout_type")
	private String btc_inout_type;
	

	public Integer getBtc_inout_order_id() {
		return btc_inout_order_id;
	}


	public void setBtc_inout_order_id(Integer btc_inout_order_id) {
		this.btc_inout_order_id = btc_inout_order_id;
	}


	public Integer getBtc_stock_id() {
		return btc_stock_id;
	}


	public void setBtc_stock_id(Integer btc_stock_id) {
		this.btc_stock_id = btc_stock_id;
	}


	public Integer getUid() {
		return uid;
	}


	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public String getBtc_inout_adr() {
		return btc_inout_adr;
	}


	public void setBtc_inout_adr(String btc_inout_adr) {
		this.btc_inout_adr = btc_inout_adr;
	}


	public BigDecimal getBtc_inout_amount() {
		return btc_inout_amount;
	}


	public void setBtc_inout_amount(BigDecimal btc_inout_amount) {
		this.btc_inout_amount = btc_inout_amount;
	}


	public BigDecimal getBtc_inout_poundage() {
		return btc_inout_poundage;
	}


	public void setBtc_inout_poundage(BigDecimal btc_inout_poundage) {
		this.btc_inout_poundage = btc_inout_poundage;
	}


	public String getBtc_inout_status() {
		return btc_inout_status;
	}


	public void setBtc_inout_status(String btc_inout_status) {
		this.btc_inout_status = btc_inout_status;
	}


	public String getBtc_inout_msg() {
		return btc_inout_msg;
	}


	public void setBtc_inout_msg(String btc_inout_msg) {
		this.btc_inout_msg = btc_inout_msg;
	}


	public String getBtc_inout_time() {
		return btc_inout_time;
	}


	public void setBtc_inout_time(String btc_inout_time) {
		this.btc_inout_time = btc_inout_time;
	}


	public String getBtc_inout_type() {
		return btc_inout_type;
	}


	public void setBtc_inout_type(String btc_inout_type) {
		this.btc_inout_type = btc_inout_type;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
