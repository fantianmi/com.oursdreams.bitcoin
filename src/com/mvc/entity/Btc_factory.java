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
@Table(name = "btc_factory")
public class Btc_factory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "date")
	private String date;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "status")
	private String status;
	@Column(name = "type")
	private String type;
	@Column(name = "eachamount")
	private BigDecimal eachamount;
	@Column(name = "userhaslimit")
	private BigDecimal userhaslimit;
	
	public BigDecimal getUserhaslimit() {
		return userhaslimit;
	}


	public void setUserhaslimit(BigDecimal userhaslimit) {
		this.userhaslimit = userhaslimit;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public BigDecimal getEachamount() {
		return eachamount;
	}


	public void setEachamount(BigDecimal eachamount) {
		this.eachamount = eachamount;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
