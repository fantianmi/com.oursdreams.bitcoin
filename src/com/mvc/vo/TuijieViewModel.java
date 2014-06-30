package com.mvc.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class TuijieViewModel implements Serializable {
	private Integer uid;
	private String username;
	private String usdtime;
	private String isAuthRealName;
	private String isGetTGaward;
	private String isRengou;
	private BigDecimal rengouget;
	
	
	public BigDecimal getRengouget() {
		return rengouget;
	}
	public void setRengouget(BigDecimal rengouget) {
		this.rengouget = rengouget;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsdtime() {
		return usdtime;
	}
	public void setUsdtime(String usdtime) {
		this.usdtime = usdtime;
	}
	public String getIsAuthRealName() {
		return isAuthRealName;
	}
	public void setIsAuthRealName(String isAuthRealName) {
		this.isAuthRealName = isAuthRealName;
	}
	public String getIsGetTGaward() {
		return isGetTGaward;
	}
	public void setIsGetTGaward(String isGetTGaward) {
		this.isGetTGaward = isGetTGaward;
	}
	public String getIsRengou() {
		return isRengou;
	}
	public void setIsRengou(String isRengou) {
		this.isRengou = isRengou;
	}
	
}
