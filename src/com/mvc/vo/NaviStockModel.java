package com.mvc.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class NaviStockModel implements Serializable {
	private Integer id;
	private String name;
	private String engName;
	private BigDecimal price;
	private BigDecimal zdf;
	private BigDecimal lastprice;
	private BigDecimal newsprice;
	private String exstock;
	private String color;
	
	public BigDecimal getNewsprice() {
		return newsprice;
	}
	public void setNewsprice(BigDecimal newsprice) {
		this.newsprice = newsprice;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getZdf() {
		return zdf;
	}
	public void setZdf(BigDecimal newsprice,BigDecimal lastprice) {
		if(lastprice.compareTo(new BigDecimal(0))!=0){
			this.zdf = newsprice.subtract(lastprice).divide(lastprice,4,BigDecimal.ROUND_HALF_UP);
		}else{
			this.zdf = new BigDecimal(0);
		}
	}
	
	public BigDecimal getLastprice() {
		return lastprice;
	}
	public void setLastprice(BigDecimal lastprice) {
		this.lastprice = lastprice;
	}
	public String getExstock() {
		return exstock;
	}
	public void setExstock(String exstock) {
		this.exstock = exstock;
	}
}
