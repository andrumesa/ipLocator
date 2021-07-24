package com.andrumes.ipLocator.model;

public class RateInfo {
	
	public RateInfo(Double usd, Double eur) {
		super();
		this.usd = usd;
		this.eur = eur;
	}
	private Double usd;
	private Double eur;
	public Double getUsd() {
		return usd;
	}
	public void setUsd(Double usd) {
		this.usd = usd;
	}
	public Double getEur() {
		return eur;
	}
	public void setEur(Double eur) {
		this.eur = eur;
	}

}
