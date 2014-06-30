package com.mvc.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class RengouViewModel implements Serializable {
	private BigDecimal eachget;
	private BigDecimal leftamount;
	private BigDecimal userbuyamountlog;
	private BigDecimal usercanbuy;
	private BigDecimal price;
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getEachget() {
		return eachget;
	}
	public void setEachget(BigDecimal eachget) {
		this.eachget = eachget;
	}
	public BigDecimal getLeftamount() {
		return leftamount;
	}
	public void setLeftamount(BigDecimal leftamount) {
		this.leftamount = leftamount;
	}
	public BigDecimal getUserbuyamountlog() {
		return userbuyamountlog;
	}
	public void setUserbuyamountlog(BigDecimal userbuyamountlog) {
		this.userbuyamountlog = userbuyamountlog;
	}
	public BigDecimal getUsercanbuy() {
		return usercanbuy;
	}
	public void setUsercanbuy(BigDecimal usercanbuy) {
		this.usercanbuy = usercanbuy;
	}
}
