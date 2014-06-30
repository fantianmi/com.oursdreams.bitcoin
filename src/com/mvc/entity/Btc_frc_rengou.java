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
@Table(name = "btc_frc_rengou")
public class Btc_frc_rengou implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "eachamount")
	private BigDecimal eachamount;
	@Column(name = "date")
	private String date;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "season")
	private String season;
	@Column(name = "status")
	private String status;
	@Column(name = "damount")
	private BigDecimal damount;

	public BigDecimal getDamount() {
		return damount;
	}


	public void setDamount(BigDecimal damount) {
		this.damount = damount;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public BigDecimal getEachamount() {
		return eachamount;
	}


	public void setEachamount(BigDecimal eachamount) {
		this.eachamount = eachamount;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public String getSeason() {
		return season;
	}


	public void setSeason(String season) {
		this.season = season;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
