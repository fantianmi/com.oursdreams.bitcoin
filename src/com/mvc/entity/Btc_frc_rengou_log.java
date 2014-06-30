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
@Table(name = "btc_frc_rengou_log")
public class Btc_frc_rengou_log implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "uid")
	private Integer uid;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "payamount")
	private BigDecimal payamount;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "season")
	private String season;
	@Column(name = "date")
	private String date;
	@Column(name = "status")
	private String stutus;
	@Column(name="uip")
	private String uip;
	
	public String getUip() {
		return uip;
	}

	public void setUip(String uip) {
		this.uip = uip;
	}



	public Integer getId() {
		return id;
	}



	public BigDecimal getPayamount() {
		return payamount;
	}



	public void setPayamount(BigDecimal payamount) {
		this.payamount = payamount;
	}



	public BigDecimal getPrice() {
		return price;
	}



	public void setPrice(BigDecimal price) {
		this.price = price;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getUid() {
		return uid;
	}



	public void setUid(Integer uid) {
		this.uid = uid;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public String getSeason() {
		return season;
	}



	public void setSeason(String season) {
		this.season = season;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getStutus() {
		return stutus;
	}



	public void setStutus(String stutus) {
		this.stutus = stutus;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
