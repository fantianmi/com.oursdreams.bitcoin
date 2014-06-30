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
@Table(name = "btc_stock")
public class Btc_stock implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_stock_id", nullable = false)
	private Integer btc_stock_id;
	@Column(name = "btc_stock_name")
	private String btc_stock_name;
	@Column(name = "btc_stock_price")
	private BigDecimal btc_stock_price;
	@Column(name="btc_stock_recharge_adr")
	private String btc_stock_recharge_adr;
	@Column(name="btc_stock_Eng_name")
	private String btc_stock_Eng_name;
	@Column(name="btc_stock_exchange_name")
	private String btc_stock_exchange_name;
	@Column(name="btc_stock_port")
	private String btc_stock_port;
	@Column(name="btc_stock_pocket_adr")
	private String btc_stock_pocket_adr;
	@Column(name="rpcusername")
	private String rpcusername;
	@Column(name="rpcpassword")
	private String rpcpassword;
	@Column(name="is_real_stock")
	private String is_real_stock;
	@Column(name="caninout")
	private int caninout;
	@Column(name="logoadr")
	private String logoadr;
	@Column(name = "rechargezxz")
	private BigDecimal rechargezxz;
	@Column(name = "tradesxf")
	private BigDecimal tradesxf;
	@Column(name = "withdrawzxz")
	private BigDecimal withdrawzxz;
	@Column(name = "withdrawzdz")
	private BigDecimal withdrawzdz;
	@Column(name = "withdrawsxf")
	private BigDecimal withdrawsxf;

	public BigDecimal getWithdrawsxf() {
		return withdrawsxf;
	}


	public void setWithdrawsxf(BigDecimal withdrawsxf) {
		this.withdrawsxf = withdrawsxf;
	}


	public BigDecimal getWithdrawzxz() {
		return withdrawzxz;
	}


	public void setWithdrawzxz(BigDecimal withdrawzxz) {
		this.withdrawzxz = withdrawzxz;
	}


	public BigDecimal getWithdrawzdz() {
		return withdrawzdz;
	}


	public void setWithdrawzdz(BigDecimal withdrawzdz) {
		this.withdrawzdz = withdrawzdz;
	}


	public BigDecimal getRechargezxz() {
		return rechargezxz;
	}


	public void setRechargezxz(BigDecimal rechargezxz) {
		this.rechargezxz = rechargezxz;
	}


	public BigDecimal getTradesxf() {
		return tradesxf;
	}


	public void setTradesxf(BigDecimal tradesxf) {
		this.tradesxf = tradesxf;
	}


	public String getLogoadr() {
		return logoadr;
	}


	public void setLogoadr(String logoadr) {
		this.logoadr = logoadr;
	}


	public int getCaninout() {
		return caninout;
	}


	public void setCaninout(int caninout) {
		this.caninout = caninout;
	}


	public String getIs_real_stock() {
		return is_real_stock;
	}


	public void setIs_real_stock(String is_real_stock) {
		this.is_real_stock = is_real_stock;
	}


	public String getRpcusername() {
		return rpcusername;
	}


	public void setRpcusername(String rpcusername) {
		this.rpcusername = rpcusername;
	}


	public String getRpcpassword() {
		return rpcpassword;
	}


	public void setRpcpassword(String rpcpassword) {
		this.rpcpassword = rpcpassword;
	}


	public String getBtc_stock_port() {
		return btc_stock_port;
	}


	public void setBtc_stock_port(String btc_stock_port) {
		this.btc_stock_port = btc_stock_port;
	}


	public String getBtc_stock_pocket_adr() {
		return btc_stock_pocket_adr;
	}


	public void setBtc_stock_pocket_adr(String btc_stock_pocket_adr) {
		this.btc_stock_pocket_adr = btc_stock_pocket_adr;
	}


	public String getBtc_stock_exchange_name() {
		return btc_stock_exchange_name;
	}


	public void setBtc_stock_exchange_name(String btc_stock_exchange_name) {
		this.btc_stock_exchange_name = btc_stock_exchange_name;
	}


	public Integer getBtc_stock_id() {
		return btc_stock_id;
	}


	public void setBtc_stock_id(Integer btc_stock_id) {
		this.btc_stock_id = btc_stock_id;
	}


	public String getBtc_stock_name() {
		return btc_stock_name;
	}


	public void setBtc_stock_name(String btc_stock_name) {
		this.btc_stock_name = btc_stock_name;
	}


	public BigDecimal getBtc_stock_price() {
		return btc_stock_price;
	}


	public void setBtc_stock_price(BigDecimal btc_stock_price) {
		this.btc_stock_price = btc_stock_price;
	}


	public String getBtc_stock_recharge_adr() {
		return btc_stock_recharge_adr;
	}


	public void setBtc_stock_recharge_adr(String btc_stock_recharge_adr) {
		this.btc_stock_recharge_adr = btc_stock_recharge_adr;
	}


	public String getBtc_stock_Eng_name() {
		return btc_stock_Eng_name;
	}


	public void setBtc_stock_Eng_name(String btc_stock_Eng_name) {
		this.btc_stock_Eng_name = btc_stock_Eng_name;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
