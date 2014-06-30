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
@Table(name = "Btc_holding")
public class Btc_holding implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_holding_id", nullable = false)
	private Integer btc_holding_id;
	@Column(name = "btc_stock_id")
	private Integer btc_stock_id;
	@Column(name = "uid")
	private int uid;
	@Column(name="btc_stock_amount")
	private BigDecimal btc_stock_amount;
	@Column(name="btc_stock_price")
	private BigDecimal btc_stock_price;


	public Integer getBtc_holding_id() {
		return btc_holding_id;
	}


	public void setBtc_holding_id(Integer btc_holding_id) {
		this.btc_holding_id = btc_holding_id;
	}


	public Integer getBtc_stock_id() {
		return btc_stock_id;
	}


	public void setBtc_stock_id(Integer btc_stock_id) {
		this.btc_stock_id = btc_stock_id;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public BigDecimal getBtc_stock_amount() {
		return btc_stock_amount;
	}


	public void setBtc_stock_amount(BigDecimal btc_stock_amount) {
		this.btc_stock_amount = btc_stock_amount;
	}


	public BigDecimal getBtc_stock_price() {
		return btc_stock_price;
	}


	public void setBtc_stock_price(BigDecimal btc_stock_price) {
		this.btc_stock_price = btc_stock_price;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
