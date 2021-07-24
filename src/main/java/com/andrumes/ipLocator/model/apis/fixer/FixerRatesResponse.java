package com.andrumes.ipLocator.model.apis.fixer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FixerRatesResponse {
private Double USD;
private Double EUR;
public Double getUSD() {
	return USD;
}
@JsonProperty("USD")
public void setUSD(Double uSD) {
	USD = uSD;
}
public Double getEUR() {
	return EUR;
}
@JsonProperty("EUR")
public void setEUR(Double eUR) {
	EUR = eUR;
}

}
