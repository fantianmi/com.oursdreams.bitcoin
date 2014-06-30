package com.mvc.entity.games;

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
@Table(name = "games_luckwheel")
public class Games_luckwheel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "uid")
	private int uid;
	@Column(name = "stockid")
	private int stockid;
	@Column(name = "type")
	private String type;
	@Column(name="amount")
	private BigDecimal amount;
	@Column(name="awards")
	private BigDecimal awards;
	@Column(name="status")
	private String status;
	@Column(name="times")
	private String times;
	@Column(name="msg")
	private String msg;


	public int getStockid() {
		return stockid;
	}

	public void setStockid(int stockid) {
		this.stockid = stockid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public BigDecimal getAmount() {
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public BigDecimal getAwards() {
		return awards;
	}


	public void setAwards(BigDecimal awards) {
		this.awards = awards;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTimes() {
		return times;
	}


	public void setTimes(String times) {
		this.times = times;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
