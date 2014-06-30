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
@Table(name = "btc_withdrawCNY_order")
public class Btc_withdrawCNY_order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_bwo_id", nullable = false)
	private Integer btc_bwo_id;
	@Column(name = "btc_bwo_amount")
	private BigDecimal btc_bwo_amount;
	@Column(name = "btc_bwo_withdraw_way")
	private String btc_bwo_withdraw_way;
	@Column(name="btc_bwo_province")
	private String btc_bwo_province;
	@Column(name="btc_bwo_city")
	private String btc_bwo_city;
	@Column(name="btc_bwo_town")
	private String btc_bwo_town;
	@Column(name="btc_bwo_bank")
	private String btc_bwo_bank;
	@Column(name="uid")
	private int uid;
	@Column(name="btc_bwo_rname")
	private String btc_bwo_rname;
	@Column(name="btc_bwo_card")
	private String btc_bwo_card;
	@Column(name="btc_bwo_time")
	private String btc_bwo_time;
	@Column(name="btc_bwo_state")
	private int btc_bwo_state;
	@Column(name="btc_bwo_content")
	private String btc_bwo_content;
	@Column(name="btc_bwo_poundage")
	private BigDecimal btc_bwo_poundage;
	@Column(name="btc_bwo_handle_time")
	private String btc_bwo_handle_time;
	

	public String getBtc_bwo_handle_time() {
		return btc_bwo_handle_time;
	}

	public void setBtc_bwo_handle_time(String btc_bwo_handle_time) {
		this.btc_bwo_handle_time = btc_bwo_handle_time;
	}

	public BigDecimal getBtc_bwo_poundage() {
		return btc_bwo_poundage;
	}

	public void setBtc_bwo_poundage(BigDecimal btc_bwo_poundage) {
		this.btc_bwo_poundage = btc_bwo_poundage;
	}

	public Integer getBtc_bwo_id() {
		return btc_bwo_id;
	}

	public void setBtc_bwo_id(Integer btc_bwo_id) {
		this.btc_bwo_id = btc_bwo_id;
	}

	public BigDecimal getBtc_bwo_amount() {
		return btc_bwo_amount;
	}

	public void setBtc_bwo_amount(BigDecimal btc_bwo_amount) {
		this.btc_bwo_amount = btc_bwo_amount;
	}

	public String getBtc_bwo_withdraw_way() {
		return btc_bwo_withdraw_way;
	}

	public void setBtc_bwo_withdraw_way(String btc_bwo_withdraw_way) {
		this.btc_bwo_withdraw_way = btc_bwo_withdraw_way;
	}

	public String getBtc_bwo_province() {
		return btc_bwo_province;
	}

	public void setBtc_bwo_province(String btc_bwo_province) {
		this.btc_bwo_province = btc_bwo_province;
	}

	public String getBtc_bwo_city() {
		return btc_bwo_city;
	}

	public void setBtc_bwo_city(String btc_bwo_city) {
		this.btc_bwo_city = btc_bwo_city;
	}

	public String getBtc_bwo_bank() {
		return btc_bwo_bank;
	}

	public String getBtc_bwo_town() {
		return btc_bwo_town;
	}

	public void setBtc_bwo_town(String btc_bwo_town) {
		this.btc_bwo_town = btc_bwo_town;
	}

	public void setBtc_bwo_bank(String btc_bwo_bank) {
		this.btc_bwo_bank = btc_bwo_bank;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getBtc_bwo_rname() {
		return btc_bwo_rname;
	}

	public void setBtc_bwo_rname(String btc_bwo_rname) {
		this.btc_bwo_rname = btc_bwo_rname;
	}

	public String getBtc_bwo_card() {
		return btc_bwo_card;
	}

	public void setBtc_bwo_card(String btc_bwo_card) {
		this.btc_bwo_card = btc_bwo_card;
	}

	public String getBtc_bwo_time() {
		return btc_bwo_time;
	}

	public void setBtc_bwo_time(String btc_bwo_time) {
		this.btc_bwo_time = btc_bwo_time;
	}

	public int getBtc_bwo_state() {
		return btc_bwo_state;
	}

	public void setBtc_bwo_state(int btc_bwo_state) {
		this.btc_bwo_state = btc_bwo_state;
	}

	public String getBtc_bwo_content() {
		return btc_bwo_content;
	}

	public void setBtc_bwo_content(String btc_bwo_content) {
		this.btc_bwo_content = btc_bwo_content;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
