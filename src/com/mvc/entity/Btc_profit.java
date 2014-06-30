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
@Table(name = "btc_profit")
public class Btc_profit implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_profit_id", nullable = false)
	private Integer btc_profit_id;
	@Column(name = "btc_profit_rechargeCNY_limit")
	private BigDecimal btc_profit_rechargeCNY_limit;
	@Column(name = "btc_profit_profit_inviteUser_get")
	private BigDecimal btc_profit_profit_inviteUser_get;
	@Column(name = "btc_profit_profit_rechargeStock_get")
	private BigDecimal btc_profit_profit_rechargeStock_get;
	@Column(name = "btc_profit_profit_trade_get")
	private BigDecimal btc_profit_profit_trade_get;
	@Column(name = "btc_profit_rechargeCNY_poundage")
	private BigDecimal btc_profit_rechargeCNY_poundage;
	@Column(name = "btc_profit_withdrawCNY_limit_min")
	private BigDecimal btc_profit_withdrawCNY_limit_min;
	@Column(name = "btc_profit_withdrawCNY_limit_max")
	private BigDecimal btc_profit_withdrawCNY_limit_max;
	@Column(name = "btc_profit_withdrawCNY_poundage")
	private BigDecimal btc_profit_withdrawCNY_poundage;
	@Column(name = "btc_profit_rechargeStock_limit")
	private BigDecimal btc_profit_rechargeStock_limit;
	@Column(name = "btc_profit_rechargeStock_poundage")
	private BigDecimal btc_profit_rechargeStock_poundage;
	@Column(name = "btc_profit_withdrawStock_limit_min")
	private BigDecimal btc_profit_withdrawStock_limit_min;
	@Column(name = "btc_profit_withdrawStock_limit_max")
	private BigDecimal btc_profit_withdrawStock_limit_max;
	@Column(name = "btc_profit_withdrawStock_poundage")
	private BigDecimal btc_profit_withdrawStock_poundage;
	@Column(name = "btc_profit_trade_poundage")
	private BigDecimal btc_profit_trade_poundage;
	@Column(name = "inviteRegist_get")
	private BigDecimal inviteRegist_get;
	@Column(name = "regist_get")
	private BigDecimal regist_get;
	@Column(name = "rechargecny_get")
	private BigDecimal rechargecny_get;
	@Column(name = "isjiaoyi")
	private String isjiaoyi;
	@Column(name = "rechargecny_getpgc")
	private BigDecimal rechargecny_getpgc;

	public BigDecimal getRechargecny_getpgc() {
		return rechargecny_getpgc;
	}

	public void setRechargecny_getpgc(BigDecimal rechargecny_getpgc) {
		this.rechargecny_getpgc = rechargecny_getpgc;
	}

	public String getIsjiaoyi() {
		return isjiaoyi;
	}

	public void setIsjiaoyi(String isjiaoyi) {
		this.isjiaoyi = isjiaoyi;
	}

	public BigDecimal getRechargecny_get() {
		return rechargecny_get;
	}

	public void setRechargecny_get(BigDecimal rechargecny_get) {
		this.rechargecny_get = rechargecny_get;
	}

	public BigDecimal getRegist_get() {
		return regist_get;
	}

	public void setRegist_get(BigDecimal regist_get) {
		this.regist_get = regist_get;
	}

	public BigDecimal getInviteRegist_get() {
		return inviteRegist_get;
	}

	public void setInviteRegist_get(BigDecimal inviteRegist_get) {
		this.inviteRegist_get = inviteRegist_get;
	}

	public BigDecimal getBtc_profit_trade_poundage() {
		return btc_profit_trade_poundage;
	}

	public void setBtc_profit_trade_poundage(BigDecimal btc_profit_trade_poundage) {
		this.btc_profit_trade_poundage = btc_profit_trade_poundage;
	}

	public Integer getBtc_profit_id() {
		return btc_profit_id;
	}

	public void setBtc_profit_id(Integer btc_profit_id) {
		this.btc_profit_id = btc_profit_id;
	}

	public BigDecimal getBtc_profit_rechargeCNY_limit() {
		return btc_profit_rechargeCNY_limit;
	}

	public BigDecimal getBtc_profit_rechargeCNY_poundage() {
		return btc_profit_rechargeCNY_poundage;
	}

	public void setBtc_profit_rechargeCNY_poundage(
			BigDecimal btc_profit_rechargeCNY_poundage) {
		this.btc_profit_rechargeCNY_poundage = btc_profit_rechargeCNY_poundage;
	}


	public BigDecimal getBtc_profit_withdrawCNY_poundage() {
		return btc_profit_withdrawCNY_poundage;
	}

	public void setBtc_profit_withdrawCNY_poundage(
			BigDecimal btc_profit_withdrawCNY_poundage) {
		this.btc_profit_withdrawCNY_poundage = btc_profit_withdrawCNY_poundage;
	}

	public BigDecimal getBtc_profit_rechargeStock_limit() {
		return btc_profit_rechargeStock_limit;
	}

	public void setBtc_profit_rechargeStock_limit(
			BigDecimal btc_profit_rechargeStock_limit) {
		this.btc_profit_rechargeStock_limit = btc_profit_rechargeStock_limit;
	}

	public BigDecimal getBtc_profit_rechargeStock_poundage() {
		return btc_profit_rechargeStock_poundage;
	}

	public void setBtc_profit_rechargeStock_poundage(
			BigDecimal btc_profit_rechargeStock_poundage) {
		this.btc_profit_rechargeStock_poundage = btc_profit_rechargeStock_poundage;
	}

	public BigDecimal getBtc_profit_withdrawCNY_limit_min() {
		return btc_profit_withdrawCNY_limit_min;
	}

	public void setBtc_profit_withdrawCNY_limit_min(
			BigDecimal btc_profit_withdrawCNY_limit_min) {
		this.btc_profit_withdrawCNY_limit_min = btc_profit_withdrawCNY_limit_min;
	}

	public BigDecimal getBtc_profit_withdrawCNY_limit_max() {
		return btc_profit_withdrawCNY_limit_max;
	}

	public void setBtc_profit_withdrawCNY_limit_max(
			BigDecimal btc_profit_withdrawCNY_limit_max) {
		this.btc_profit_withdrawCNY_limit_max = btc_profit_withdrawCNY_limit_max;
	}

	public BigDecimal getBtc_profit_withdrawStock_limit_min() {
		return btc_profit_withdrawStock_limit_min;
	}

	public void setBtc_profit_withdrawStock_limit_min(
			BigDecimal btc_profit_withdrawStock_limit_min) {
		this.btc_profit_withdrawStock_limit_min = btc_profit_withdrawStock_limit_min;
	}

	public BigDecimal getBtc_profit_withdrawStock_limit_max() {
		return btc_profit_withdrawStock_limit_max;
	}

	public void setBtc_profit_withdrawStock_limit_max(
			BigDecimal btc_profit_withdrawStock_limit_max) {
		this.btc_profit_withdrawStock_limit_max = btc_profit_withdrawStock_limit_max;
	}

	public BigDecimal getBtc_profit_withdrawStock_poundage() {
		return btc_profit_withdrawStock_poundage;
	}

	public void setBtc_profit_withdrawStock_poundage(
			BigDecimal btc_profit_withdrawStock_poundage) {
		this.btc_profit_withdrawStock_poundage = btc_profit_withdrawStock_poundage;
	}

	public void setBtc_profit_rechargeCNY_limit(
			BigDecimal btc_profit_rechargeCNY_limit) {
		this.btc_profit_rechargeCNY_limit = btc_profit_rechargeCNY_limit;
	}

	public BigDecimal getBtc_profit_profit_inviteUser_get() {
		return btc_profit_profit_inviteUser_get;
	}

	public void setBtc_profit_profit_inviteUser_get(
			BigDecimal btc_profit_profit_inviteUser_get) {
		this.btc_profit_profit_inviteUser_get = btc_profit_profit_inviteUser_get;
	}

	public BigDecimal getBtc_profit_profit_rechargeStock_get() {
		return btc_profit_profit_rechargeStock_get;
	}

	public void setBtc_profit_profit_rechargeStock_get(
			BigDecimal btc_profit_profit_rechargeStock_get) {
		this.btc_profit_profit_rechargeStock_get = btc_profit_profit_rechargeStock_get;
	}

	public BigDecimal getBtc_profit_profit_trade_get() {
		return btc_profit_profit_trade_get;
	}

	public void setBtc_profit_profit_trade_get(
			BigDecimal btc_profit_profit_trade_get) {
		this.btc_profit_profit_trade_get = btc_profit_profit_trade_get;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
