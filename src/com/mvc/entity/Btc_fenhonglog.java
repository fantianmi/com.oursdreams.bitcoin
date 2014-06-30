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
@Table(name = "btc_fenhonglog")
public class Btc_fenhonglog implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fenhong_id", nullable = false)
	private Integer fenhong_id;
	@Column(name = "fenhong_season")
	private int fenhong_season;
	@Column(name = "fenhong_time")
	private String fenhong_time;
	@Column(name = "fenhong_status")
	private String fenhong_status;
	@Column(name="fenhong_amount")
	private BigDecimal fenhong_amount;
	
	public Integer getFenhong_id() {
		return fenhong_id;
	}

	public void setFenhong_id(Integer fenhong_id) {
		this.fenhong_id = fenhong_id;
	}



	public int getFenhong_season() {
		return fenhong_season;
	}



	public void setFenhong_season(int fenhong_season) {
		this.fenhong_season = fenhong_season;
	}



	public String getFenhong_time() {
		return fenhong_time;
	}

	public void setFenhong_time(String fenhong_time) {
		this.fenhong_time = fenhong_time;
	}

	public String getFenhong_status() {
		return fenhong_status;
	}

	public void setFenhong_status(String fenhong_status) {
		this.fenhong_status = fenhong_status;
	}



	public BigDecimal getFenhong_amount() {
		return fenhong_amount;
	}



	public void setFenhong_amount(BigDecimal fenhong_amount) {
		this.fenhong_amount = fenhong_amount;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
