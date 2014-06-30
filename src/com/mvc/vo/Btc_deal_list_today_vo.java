package com.mvc.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class Btc_deal_list_today_vo implements Serializable {
	private BigDecimal btc_deal_today_RateMax;
	private BigDecimal btc_deal_today_RateMin;
	private BigDecimal btc_deal_today_Total;
	private BigDecimal btc_deal_today_Total_cny;
	private BigDecimal btc_deal_today_Latest;
	private BigDecimal btc_deal_yestoday_last;
	
	public BigDecimal getZhangFu(BigDecimal btc_deal_today_Latest,BigDecimal btc_deal_yestoday_last){
		if(btc_deal_yestoday_last.compareTo(new BigDecimal(0))!=0){
			return btc_deal_today_Latest.subtract(btc_deal_yestoday_last).divide(btc_deal_yestoday_last,4,BigDecimal.ROUND_HALF_UP);
		}else{
			return new BigDecimal(0);
		}
	}
	
	public BigDecimal getBtc_deal_yestoday_last() {
		return btc_deal_yestoday_last;
	}
	public void setBtc_deal_yestoday_last(BigDecimal btc_deal_yestoday_last) {
		this.btc_deal_yestoday_last = btc_deal_yestoday_last;
	}
	public BigDecimal getBtc_deal_today_Total_cny() {
		return btc_deal_today_Total_cny;
	}
	public void setBtc_deal_today_Total_cny(BigDecimal btc_deal_today_Total_cny) {
		this.btc_deal_today_Total_cny = btc_deal_today_Total_cny;
	}
	public BigDecimal getBtc_deal_today_Latest() {
		return btc_deal_today_Latest;
	}
	public void setBtc_deal_today_Latest(BigDecimal btc_deal_today_Latest) {
		this.btc_deal_today_Latest = btc_deal_today_Latest;
	}
	public BigDecimal getBtc_deal_today_RateMax() {
		return btc_deal_today_RateMax;
	}
	public void setBtc_deal_today_RateMax(BigDecimal btc_deal_today_RateMax) {
		this.btc_deal_today_RateMax = btc_deal_today_RateMax;
	}
	public BigDecimal getBtc_deal_today_RateMin() {
		return btc_deal_today_RateMin;
	}
	public void setBtc_deal_today_RateMin(BigDecimal btc_deal_today_RateMin) {
		this.btc_deal_today_RateMin = btc_deal_today_RateMin;
	}
	public BigDecimal getBtc_deal_today_Total() {
		return btc_deal_today_Total;
	}
	public void setBtc_deal_today_Total(BigDecimal btc_deal_today_Total) {
		this.btc_deal_today_Total = btc_deal_today_Total;
	}
}
