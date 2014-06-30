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
@Table(name = "btc_rechargeCNY_order")
public class Btc_rechargeCNY_order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bro_id", nullable = false)
	private Integer bro_id;
	@Column(name = "bro_recharge_way")
	private String bro_recharge_way;
	@Column(name = "bro_recharge_acount")
	private BigDecimal bro_recharge_acount;
	@Column(name="bro_factorage")
	private BigDecimal bro_factorage;
	@Column(name="bro_recharge_time")
	private String bro_recharge_time;
	@Column(name="bro_remark")
	private String bro_remark;
	@Column(name="uid")
	private int uid;
	@Column(name="bro_state")
	private int bro_state;
	@Column(name="bro_sname")
	private String bro_sname;
	@Column(name="bro_rname")
	private String bro_rname;
	@Column(name="BillNo")
	private String BillNo;

	public String getBillNo() {
		return BillNo;
	}
	public void setBillNo(String billNo) {
		BillNo = billNo;
	}


	public Integer getBro_id() {
		return bro_id;
	}


	public void setBro_id(Integer bro_id) {
		this.bro_id = bro_id;
	}


	public String getBro_recharge_way() {
		return bro_recharge_way;
	}


	public void setBro_recharge_way(String bro_recharge_way) {
		this.bro_recharge_way = bro_recharge_way;
	}

	public BigDecimal getBro_recharge_acount() {
		return bro_recharge_acount;
	}


	public void setBro_recharge_acount(BigDecimal bro_recharge_acount) {
		this.bro_recharge_acount = bro_recharge_acount;
	}


	public BigDecimal getBro_factorage() {
		return bro_factorage;
	}


	public void setBro_factorage(BigDecimal bro_factorage) {
		this.bro_factorage = bro_factorage;
	}


	public String getBro_recharge_time() {
		return bro_recharge_time;
	}


	public void setBro_recharge_time(String bro_recharge_time) {
		this.bro_recharge_time = bro_recharge_time;
	}


	public String getBro_remark() {
		return bro_remark;
	}


	public void setBro_remark(String bro_remark) {
		this.bro_remark = bro_remark;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getBro_state() {
		return bro_state;
	}


	public void setBro_state(int bro_state) {
		this.bro_state = bro_state;
	}


	public String getBro_sname() {
		return bro_sname;
	}


	public void setBro_sname(String bro_sname) {
		this.bro_sname = bro_sname;
	}


	public String getBro_rname() {
		return bro_rname;
	}


	public void setBro_rname(String bro_rname) {
		this.bro_rname = bro_rname;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
