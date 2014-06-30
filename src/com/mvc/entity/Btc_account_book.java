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
@Table(name = "btc_account_book")
public class Btc_account_book implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ab_id", nullable = false)
	private Integer ab_id;
	@Column(name = "uid")
	private int uid;
	@Column(name = "ab_cny")
	private BigDecimal ab_cny;
	@Column(name="ab_btc")
	private BigDecimal ab_btc;

	public Integer getAb_id() {
		return ab_id;
	}

	public void setAb_id(Integer ab_id) {
		this.ab_id = ab_id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public BigDecimal getAb_cny() {
		return ab_cny;
	}

	public void setAb_cny(BigDecimal ab_cny) {
		this.ab_cny = ab_cny;
	}

	public BigDecimal getAb_btc() {
		return ab_btc;
	}

	public void setAb_btc(BigDecimal ab_btc) {
		this.ab_btc = ab_btc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
