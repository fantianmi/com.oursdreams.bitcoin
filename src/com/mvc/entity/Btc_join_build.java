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
@Table(name = "btc_join_build")
public class Btc_join_build implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "date")
	private String date;
	@Column(name = "type")
	private String type;
	@Column(name = "uid")
	private int uid;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "getamount")
	private BigDecimal getamount;
	@Column(name = "getdate")
	private String getdate;
	@Column(name = "status")
	private String status;
	@Column(name = "xl")
	private BigDecimal xl;

	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public BigDecimal getGetamount() {
		return getamount;
	}

	public BigDecimal getXl() {
		return xl;
	}



	public void setXl(BigDecimal xl) {
		this.xl = xl;
	}



	public void setGetamount(BigDecimal getamount) {
		this.getamount = getamount;
	}



	public String getGetdate() {
		return getdate;
	}



	public void setGetdate(String getdate) {
		this.getdate = getdate;
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



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}


	public int getUid() {
		return uid;
	}



	public void setUid(int uid) {
		this.uid = uid;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
