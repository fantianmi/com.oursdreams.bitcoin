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
@Table(name = "btc_trade_category")
public class Btc_trade_category implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tradecid", nullable = false)
	private Integer tradecid;
	@Column(name = "tradec_stockid")
	private Integer tradec_stockid;
	@Column(name = "tradec_exstock")
	private String tradec_exstock;
	@Column(name = "tradec_price")
	private BigDecimal tradec_price;

	public Integer getTradecid() {
		return tradecid;
	}

	public void setTradecid(Integer tradecid) {
		this.tradecid = tradecid;
	}

	public Integer getTradec_stockid() {
		return tradec_stockid;
	}

	public void setTradec_stockid(Integer tradec_stockid) {
		this.tradec_stockid = tradec_stockid;
	}



	public String getTradec_exstock() {
		return tradec_exstock;
	}



	public void setTradec_exstock(String tradec_exstock) {
		this.tradec_exstock = tradec_exstock;
	}



	public BigDecimal getTradec_price() {
		return tradec_price;
	}



	public void setTradec_price(BigDecimal tradec_price) {
		this.tradec_price = tradec_price;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
