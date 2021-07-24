package com.andrumes.ipLocator.handlers;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.andrumes.ipLocator.model.LocatorResponse;
import com.andrumes.ipLocator.model.apis.fixer.FixerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RatesHandler extends LocatorHandler {

	private String urlService;
	private String token;

	public RatesHandler(String url, String token) {
		this.urlService = url;
		this.token = token;
	}
	
	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	@Override
	public boolean process(LocatorResponse locatorResponse) {
	    StringBuilder urlStr = new StringBuilder(this.getUrlService());
		boolean result = false;
		
		urlStr.append("?access_key=");
		urlStr.append(this.getToken());
		urlStr.append("&base=");
		urlStr.append(locatorResponse.getBaseCurrency());
		urlStr.append("&symbols=USD,EUR");
	    	    
		HttpGet request = new HttpGet(urlStr.toString());
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			// Get HttpResponse Status
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					ObjectMapper mapper = new ObjectMapper();
					String resultStr = EntityUtils.toString(entity);
					FixerResponse ratesReponse = mapper.readValue(resultStr, FixerResponse.class);
					if(ratesReponse.getSuccess()==true)
					{
						result = true;
						locatorResponse.setRates(ratesReponse.getRates());
						locatorResponse.setMessage("ACCEPTED:Rates info retrieved");
						if (this.getNextHandler() != null) {
							result = this.getNextHandler().process(locatorResponse);
						}
					}else
					{
						if(ratesReponse.getError().getInfo()!=null)
						{
							locatorResponse.setMessage("FAILED:Can not retrieve Rates info "+ratesReponse.getError().getInfo());
						}else if(ratesReponse.getError().getType()!=null)
						{
							locatorResponse.setMessage("FAILED:Can not retrieve Rates info "+ratesReponse.getError().getType());	
						}else
						{
							locatorResponse.setMessage("FAILED:Can not retrieve Rates info,there is not error message");	
						}
							
					}
				} else {
					locatorResponse.setMessage("FAILED:Can not retrieve Rates info there is a problem receiving the data");
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			locatorResponse.setMessage("FAILED:Can not retrieve Rates Info ,there is a problem reading the data");
		} catch (IOException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			locatorResponse.setMessage("FAILED:Can not retrieve Rates Info ,there is a communication problem");
		} 
		return result;
	}

	public String getUrlService() {
		return urlService;
	}

	public void setUrlService(String urlService) {
		this.urlService = urlService;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
