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
@Table(name = "btc_fhDDC")
public class Btc_fhDDC implements Serializable {
	public Integer getFh_id() {
		return fh_id;
	}



	public void setFh_id(Integer fh_id) {
		this.fh_id = fh_id;
	}



	public Integer getUid() {
		return uid;
	}



	public void setUid(Integer uid) {
		this.uid = uid;
	}



	public String getUusername() {
		return uusername;
	}



	public void setUusername(String uusername) {
		this.uusername = uusername;
	}



	public String getUrole() {
		return urole;
	}



	public void setUrole(String urole) {
		this.urole = urole;
	}



	public BigDecimal getJiaoyie() {
		return jiaoyie;
	}



	public void setJiaoyie(BigDecimal jiaoyie) {
		this.jiaoyie = jiaoyie;
	}



	public BigDecimal getGetddc() {
		return getddc;
	}



	public void setGetddc(BigDecimal getddc) {
		this.getddc = getddc;
	}



	public String getRegisttme() {
		return registtme;
	}



	public void setRegisttme(String registtme) {
		this.registtme = registtme;
	}



	public String getUstatus() {
		return ustatus;
	}



	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}



	public String getUcertification() {
		return ucertification;
	}



	public void setUcertification(String ucertification) {
		this.ucertification = ucertification;
	}



	public String getUname() {
		return uname;
	}



	public void setUname(String uname) {
		this.uname = uname;
	}



	public String getTjtime() {
		return tjtime;
	}



	public void setTjtime(String tjtime) {
		this.tjtime = tjtime;
	}



	public String getFftime() {
		return fftime;
	}



	public void setFftime(String fftime) {
		this.fftime = fftime;
	}



	public String getFstatus() {
		return fstatus;
	}



	public void setFstatus(String fstatus) {
		this.fstatus = fstatus;
	}



	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fh_id", nullable = false)
	private Integer fh_id;
	@Column(name = "uid")
	private Integer uid;
	@Column(name = "uusername")
	private String uusername;
	@Column(name="urole")
	private String urole;
	@Column(name="jiaoyie")
	private BigDecimal jiaoyie;
	@Column(name="getddc")
	private BigDecimal getddc;
	@Column(name="registtme")
	private String registtme;
	@Column(name="ustatus")
	private String ustatus;
	@Column(name="ucertification")
	private String ucertification;
	@Column(name="uname")
	private String uname;
	@Column(name="tjtime")
	private String tjtime;
	@Column(name="fftime")
	private String fftime;
	@Column(name="fstatus")
	private String fstatus;
	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
