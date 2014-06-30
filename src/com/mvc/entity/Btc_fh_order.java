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
@Table(name = "btc_fh_order")
public class Btc_fh_order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fh_order_id", nullable = false)
	private Integer fh_order_id;
	@Column(name = "uid")
	private int uid;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name="isdeliver")
	private String isdeliver;
	@Column(name="isget")
	private String isget;
	@Column(name="delivertime")
	private String delivertime;
	@Column(name="gettime")
	private String gettime;
	@Column(name="season")
	private int season;

	public Integer getFh_order_id() {
		return fh_order_id;
	}

	public void setFh_order_id(Integer fh_order_id) {
		this.fh_order_id = fh_order_id;
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

	public String getIsdeliver() {
		return isdeliver;
	}

	public void setIsdeliver(String isdeliver) {
		this.isdeliver = isdeliver;
	}

	public String getIsget() {
		return isget;
	}

	public void setIsget(String isget) {
		this.isget = isget;
	}

	public String getDelivertime() {
		return delivertime;
	}

	public void setDelivertime(String delivertime) {
		this.delivertime = delivertime;
	}

	public String getGettime() {
		return gettime;
	}

	public void setGettime(String gettime) {
		this.gettime = gettime;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
