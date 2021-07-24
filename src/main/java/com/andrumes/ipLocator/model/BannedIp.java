package com.andrumes.ipLocator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="BANNED_IP")
public class BannedIp {
	
	
	
	public BannedIp()
	{

	}
	
	public BannedIp(String ip)
	{
		this.ip=ip;
	}
	
	@Id
	@Column(name = "id_ip")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idIp;
	
	@NotBlank
	@Column(name = "ip")
	private String ip;
	
	public Integer getIdIp() {
		return idIp;
	}

	public void setIdIp(Integer idIp) {
		this.idIp = idIp;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}



}
