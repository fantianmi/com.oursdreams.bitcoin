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
@Table(name = "btc_content")
public class Btc_content implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "btc_content_id", nullable = false)
	private Integer btc_content_id;
	@Column(name = "btc_content_stock_id")
	private Integer btc_content_stock_id;
	@Column(name = "btc_content_type")
	private String btc_content_type;
	@Column(name = "btc_content_title")
	private String btc_content_title;
	@Column(name = "btc_content_msg")
	private String btc_content_msg;
	@Column(name = "btc_content_time")
	private String btc_content_time;
	@Column(name = "author")
	private String author;
	
	
	

	public String getAuthor() {
		return author;
	}




	public void setAuthor(String author) {
		this.author = author;
	}




	public Integer getBtc_content_id() {
		return btc_content_id;
	}




	public void setBtc_content_id(Integer btc_content_id) {
		this.btc_content_id = btc_content_id;
	}




	public Integer getBtc_content_stock_id() {
		return btc_content_stock_id;
	}




	public void setBtc_content_stock_id(Integer btc_content_stock_id) {
		this.btc_content_stock_id = btc_content_stock_id;
	}




	public String getBtc_content_title() {
		return btc_content_title;
	}




	public void setBtc_content_title(String btc_content_title) {
		this.btc_content_title = btc_content_title;
	}




	public String getBtc_content_msg() {
		return btc_content_msg;
	}




	public void setBtc_content_msg(String btc_content_msg) {
		this.btc_content_msg = btc_content_msg;
	}




	public String getBtc_content_type() {
		return btc_content_type;
	}




	public void setBtc_content_type(String btc_content_type) {
		this.btc_content_type = btc_content_type;
	}




	public String getBtc_content_time() {
		return btc_content_time;
	}




	public void setBtc_content_time(String btc_content_time) {
		this.btc_content_time = btc_content_time;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
