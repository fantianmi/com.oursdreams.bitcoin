package com.mvc.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "btc_votestock")
public class Btc_votestock implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vid", nullable = false)
	private Integer vid;
	@Column(name = "vstockname")
	private String vstockname;
	@Column(name = "vstockEngname")
	private String vstockEngname;
	@Column(name="vstockfullName")
	private String vstockfullName;
	@Column(name="vamount")
	private int vamount;
	@Column(name="vstatus")
	private String vstatus;
	@Column(name="username")
	private String username;


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Integer getVid() {
		return vid;
	}


	public void setVid(Integer vid) {
		this.vid = vid;
	}


	public String getVstockname() {
		return vstockname;
	}


	public void setVstockname(String vstockname) {
		this.vstockname = vstockname;
	}


	public String getVstockEngname() {
		return vstockEngname;
	}


	public void setVstockEngname(String vstockEngname) {
		this.vstockEngname = vstockEngname;
	}


	public String getVstockfullName() {
		return vstockfullName;
	}


	public void setVstockfullName(String vstockfullName) {
		this.vstockfullName = vstockfullName;
	}


	public int getVamount() {
		return vamount;
	}


	public void setVamount(int vamount) {
		this.vamount = vamount;
	}


	public String getVstatus() {
		return vstatus;
	}


	public void setVstatus(String vstatus) {
		this.vstatus = vstatus;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
