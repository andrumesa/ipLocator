package com.andrumes.ipLocator.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="CURRENCY_BY_COUNTRY")
public class CurrencyByCountry {
	

	@Id
	@Column(name = "id_cur")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCur;
	
	@NotBlank
	@Column(name = "country")
	private String country;
	
	@NotBlank
	@Column(name = "country_code")
	private String  countryCode;
	
	@NotBlank
	@Column(name = "currency")
	private String currency; 
	
	@NotBlank
	@Column(name = "code")
	private String code;

	
	public Integer getIdCur() {
		return idCur;
	}

	public void setIdCur(int idCur) {
		this.idCur = idCur;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
