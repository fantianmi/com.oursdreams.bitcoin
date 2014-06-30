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
@Table(name = "btc_deposits")
public class Btc_deposits implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_deposits_id", nullable = false)
	private Integer btc_deposits_id;
	@Column(name = "btc_deposits_tx_hash")
	private String btc_deposits_tx_hash;
	@Column(name = "btc_deposits_guid")
	private String btc_deposits_guid;
	@Column(name="btc_deposits_value")
	private BigDecimal btc_deposits_value;
	@Column(name="uid")
	private int uid;
	
	
	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public Integer getBtc_deposits_id() {
		return btc_deposits_id;
	}


	public void setBtc_deposits_id(Integer btc_deposits_id) {
		this.btc_deposits_id = btc_deposits_id;
	}


	public String getBtc_deposits_tx_hash() {
		return btc_deposits_tx_hash;
	}


	public void setBtc_deposits_tx_hash(String btc_deposits_tx_hash) {
		this.btc_deposits_tx_hash = btc_deposits_tx_hash;
	}


	public String getBtc_deposits_guid() {
		return btc_deposits_guid;
	}


	public void setBtc_deposits_guid(String btc_deposits_guid) {
		this.btc_deposits_guid = btc_deposits_guid;
	}


	public BigDecimal getBtc_deposits_value() {
		return btc_deposits_value;
	}


	public void setBtc_deposits_value(BigDecimal btc_deposits_value) {
		this.btc_deposits_value = btc_deposits_value;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
