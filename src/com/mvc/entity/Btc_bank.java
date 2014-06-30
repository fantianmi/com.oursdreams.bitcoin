package com.mvc.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "btc_bank")
public class Btc_bank implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bankid", nullable = false)
	private Integer bankid;
	@Column(name = "uid")
	private int uid;
	@Column(name = "bankname")
	private String bankname;
	@Column(name="province")
	private String province;
	@Column(name="city")
	private String city;
	@Column(name="town")
	private String town;
	@Column(name="name")
	private String name;
	@Column(name="depositbank")
	private String depositbank;
	@Column(name="card")
	private String card;
	@Column(name="status")
	private String status;

	public Integer getBankid() {
		return bankid;
	}


	public void setBankid(Integer bankid) {
		this.bankid = bankid;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getBankname() {
		return bankname;
	}


	public void setBankname(String bankname) {
		this.bankname = bankname;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getTown() {
		return town;
	}


	public void setTown(String town) {
		this.town = town;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDepositbank() {
		return depositbank;
	}


	public void setDepositbank(String depositbank) {
		this.depositbank = depositbank;
	}


	public String getCard() {
		return card;
	}


	public void setCard(String card) {
		this.card = card;
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
