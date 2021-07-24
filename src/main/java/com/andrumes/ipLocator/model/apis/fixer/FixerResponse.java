package com.andrumes.ipLocator.model.apis.fixer;

public class FixerResponse {
	
	
	//{"success":true,"timestamp":1627015204,"base":"EUR","date":"2021-07-23","rates":{"USD":1.17705,"EUR":1}}
	private Boolean success;
	private Long timestamp;
	private String base;
	private String date;
	private FixerRatesResponse rates;
	private FixerError error;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public FixerRatesResponse getRates() {
		return rates;
	}
	public void setRates(FixerRatesResponse rates) {
		this.rates = rates;
	}
	public FixerError getError() {
		return error;
	}
	public void setError(FixerError error) {
		this.error = error;
	}

}
