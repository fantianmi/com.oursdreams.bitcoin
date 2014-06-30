package com.mvc.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class Btc_deal_list_vo implements Serializable {
	private BigDecimal btc_deal_RateOpen;
	private BigDecimal btc_deal_RateClose;
	private BigDecimal btc_deal_RateMax;
	private BigDecimal btc_deal_RateMin;
	private BigDecimal btc_deal_Total;
	private long btc_deal_time;
	
	public BigDecimal getBtc_deal_RateOpen() {
		return btc_deal_RateOpen;
	}
	public void setBtc_deal_RateOpen(BigDecimal btc_deal_RateOpen) {
		this.btc_deal_RateOpen = btc_deal_RateOpen;
	}
	public BigDecimal getBtc_deal_RateClose() {
		return btc_deal_RateClose;
	}
	public void setBtc_deal_RateClose(BigDecimal btc_deal_RateClose) {
		this.btc_deal_RateClose = btc_deal_RateClose;
	}
	public BigDecimal getBtc_deal_RateMax() {
		return btc_deal_RateMax;
	}
	public void setBtc_deal_RateMax(BigDecimal btc_deal_RateMax) {
		this.btc_deal_RateMax = btc_deal_RateMax;
	}
	public BigDecimal getBtc_deal_RateMin() {
		return btc_deal_RateMin;
	}
	public void setBtc_deal_RateMin(BigDecimal btc_deal_RateMin) {
		this.btc_deal_RateMin = btc_deal_RateMin;
	}
	public BigDecimal getBtc_deal_Total() {
		return btc_deal_Total;
	}
	public void setBtc_deal_Total(BigDecimal btc_deal_Total) {
		this.btc_deal_Total = btc_deal_Total;
	}
	public long getBtc_deal_time() {
		return btc_deal_time;
	}
	public void setBtc_deal_time(long btc_deal_time) {
		this.btc_deal_time = btc_deal_time;
	}
}
