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
@Table(name = "btc_deal_list")
public class Btc_deal_list implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_deal_id", nullable = false)
	private Integer btc_deal_id;
	@Column(name = "buid")
	private Integer buid;
	@Column(name = "suid")
	private Integer suid;
	@Column(name = "btc_deal_Rate")
	private BigDecimal btc_deal_Rate;
	@Column(name = "buy_Rate")
	private BigDecimal buy_Rate;
	@Column(name = "btc_deal_quantity")
	private BigDecimal btc_deal_quantity;
	@Column(name="btc_deal_total")
	private BigDecimal btc_deal_total;
	@Column(name="bro_btc_id")
	private Integer bro_btc_id;
	@Column(name="bso_btc_id")
	private Integer bso_btc_id;
	@Column(name="btc_deal_time")
	private String btc_deal_time;
	@Column(name="btc_stock_id")
	private Integer btc_stock_id;
	@Column(name="btc_deal_type")
	private String btc_deal_type;
	@Column(name="btc_exstock_name")
	private String btc_exstock_name;
	
	
	public Btc_deal_list(){};
	
	public Btc_deal_list(int brid,int bsid,BigDecimal dealQuantity,BigDecimal dealRate,String sucesstime,BigDecimal total,int stockid,String type,String exstock,int buid,int suid,BigDecimal buyrate){
		this.bro_btc_id=brid;
		this.bso_btc_id=bsid;
		this.btc_deal_quantity=dealQuantity;
		this.btc_deal_Rate=dealRate;
		this.btc_deal_time=sucesstime;
		this.btc_deal_total=total;
		this.btc_stock_id=stockid;
		this.btc_deal_type=type;
		this.btc_exstock_name=exstock;
		this.buid=buid;
		this.suid=suid;
		this.buy_Rate=buyrate;
	}

	public BigDecimal getBuy_Rate() {
		return buy_Rate;
	}


	public void setBuy_Rate(BigDecimal buy_Rate) {
		this.buy_Rate = buy_Rate;
	}


	public Integer getBuid() {
		return buid;
	}


	public void setBuid(Integer buid) {
		this.buid = buid;
	}


	public Integer getSuid() {
		return suid;
	}


	public void setSuid(Integer suid) {
		this.suid = suid;
	}


	public String getBtc_exstock_name() {
		return btc_exstock_name;
	}


	public void setBtc_exstock_name(String btc_exstock_name) {
		this.btc_exstock_name = btc_exstock_name;
	}


	public String getBtc_deal_type() {
		return btc_deal_type;
	}


	public void setBtc_deal_type(String btc_deal_type) {
		this.btc_deal_type = btc_deal_type;
	}


	public Integer getBtc_stock_id() {
		return btc_stock_id;
	}


	public void setBtc_stock_id(Integer btc_stock_id) {
		this.btc_stock_id = btc_stock_id;
	}


	public Integer getBtc_deal_id() {
		return btc_deal_id;
	}


	public void setBtc_deal_id(Integer btc_deal_id) {
		this.btc_deal_id = btc_deal_id;
	}


	public BigDecimal getBtc_deal_Rate() {
		return btc_deal_Rate;
	}


	public void setBtc_deal_Rate(BigDecimal btc_deal_Rate) {
		this.btc_deal_Rate = btc_deal_Rate;
	}


	public BigDecimal getBtc_deal_quantity() {
		return btc_deal_quantity;
	}


	public void setBtc_deal_quantity(BigDecimal btc_deal_quantity) {
		this.btc_deal_quantity = btc_deal_quantity;
	}


	public BigDecimal getBtc_deal_total() {
		return btc_deal_total;
	}


	public void setBtc_deal_total(BigDecimal btc_deal_total) {
		this.btc_deal_total = btc_deal_total;
	}


	public Integer getBro_btc_id() {
		return bro_btc_id;
	}


	public void setBro_btc_id(Integer bro_btc_id) {
		this.bro_btc_id = bro_btc_id;
	}


	public Integer getBso_btc_id() {
		return bso_btc_id;
	}


	public void setBso_btc_id(Integer bso_btc_id) {
		this.bso_btc_id = bso_btc_id;
	}


	public String getBtc_deal_time() {
		return btc_deal_time;
	}


	public void setBtc_deal_time(String btc_deal_time) {
		this.btc_deal_time = btc_deal_time;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
