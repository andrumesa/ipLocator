package com.andrumes.ipLocator.model;

import java.util.Date;

import com.andrumes.ipLocator.model.apis.fixer.FixerRatesResponse;

public class LocatorResponse extends LocatorRequest 
{
	private String isoCountryCode;
	private String countryName;
    private String baseCurrency;
    private Date  date;
    private FixerRatesResponse rates;
    private boolean result;
    private String message="";
    
    public LocatorResponse(LocatorRequest request)
    {
    	this.setIp(request.getIp());
    	this.setContent(request.getContent());
    }
    
    public LocatorResponse()
    {
    }
    
	public String getIsoCountryCode() {
		return isoCountryCode;
	}

	public void setIsoCountryCode(String isoCountryCode) {
		this.isoCountryCode = isoCountryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		StringBuilder Builder= new StringBuilder(this.message);
		Builder.append("=>");
		Builder.append(message);
		this.message =Builder.toString();
	}

	public FixerRatesResponse getRates() {
		return rates;
	}

	public void setRates(FixerRatesResponse rates) {
		this.rates = rates;
	}


	
}
