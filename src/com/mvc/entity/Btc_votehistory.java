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
@Table(name = "btc_votehistory")
public class Btc_votehistory implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vh_id", nullable = false)
	private Integer vh_id;
	@Column(name = "vh_vid")
	private Integer vh_vid;
	@Column(name = "vh_uid")
	private Integer vh_uid;



	public Integer getVh_id() {
		return vh_id;
	}



	public void setVh_id(Integer vh_id) {
		this.vh_id = vh_id;
	}



	public Integer getVh_vid() {
		return vh_vid;
	}



	public void setVh_vid(Integer vh_vid) {
		this.vh_vid = vh_vid;
	}



	public Integer getVh_uid() {
		return vh_uid;
	}



	public void setVh_uid(Integer vh_uid) {
		this.vh_uid = vh_uid;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
