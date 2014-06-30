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
@Table(name = "btc_frc_info")
public class Btc_frc_info implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "amount")
	private BigDecimal amount;
	@Column(name = "factory")
	private BigDecimal factory;
	@Column(name = "rengou")
	private BigDecimal rengou;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getFactory() {
		return factory;
	}

	public void setFactory(BigDecimal factory) {
		this.factory = factory;
	}

	public BigDecimal getRengou() {
		return rengou;
	}



	public void setRengou(BigDecimal rengou) {
		this.rengou = rengou;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
