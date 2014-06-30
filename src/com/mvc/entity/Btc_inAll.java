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
@Table(name = "btc_inAll")
public class Btc_inAll implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_inAll_id", nullable = false)
	private Integer btc_inAll_id;
	@Column(name = "btc_inAll_name")
	private String btc_inAll_name;
	@Column(name = "btc_inAll_amount")
	private BigDecimal btc_inAll_amount;

	public Integer getBtc_inAll_id() {
		return btc_inAll_id;
	}


	public void setBtc_inAll_id(Integer btc_inAll_id) {
		this.btc_inAll_id = btc_inAll_id;
	}


	public String getBtc_inAll_name() {
		return btc_inAll_name;
	}


	public void setBtc_inAll_name(String btc_inAll_name) {
		this.btc_inAll_name = btc_inAll_name;
	}


	public BigDecimal getBtc_inAll_amount() {
		return btc_inAll_amount;
	}


	public void setBtc_inAll_amount(BigDecimal btc_inAll_amount) {
		this.btc_inAll_amount = btc_inAll_amount;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
