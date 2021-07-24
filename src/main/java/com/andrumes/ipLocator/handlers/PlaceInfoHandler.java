package com.andrumes.ipLocator.handlers;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;

import com.andrumes.ipLocator.model.LocatorResponse;
import com.andrumes.ipLocator.model.apis.Ipcountry.IpCountryInfoResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PlaceInfoHandler extends LocatorHandler {
	
	private String urlService;

	public PlaceInfoHandler(String url) {
	 this.setUrlService(url);
	}

	private final CloseableHttpClient httpClient = HttpClients.createDefault();

	@Override
	public boolean process(LocatorResponse locatorResponse) {
	    StringBuilder urlStr = new StringBuilder(this.getUrlService());
		boolean result = false;
		urlStr.append(locatorResponse.getIp());
		locatorResponse.setDate(new Date());
		HttpGet request = new HttpGet(urlStr.toString());
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			// Get HttpResponse Status
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					ObjectMapper mapper = new ObjectMapper();
					String resultStr = EntityUtils.toString(entity);
					IpCountryInfoResponse ipInfoCountry = mapper.readValue(resultStr, IpCountryInfoResponse.class);
					locatorResponse.setCountryName(ipInfoCountry.getCountryName());
					locatorResponse.setIsoCountryCode(ipInfoCountry.getCountryCode());
					locatorResponse.setMessage("ACCEPTED:Country info retrieved");
					if (this.getNextHandler() != null) {
						result = this.getNextHandler().process(locatorResponse);
					}
				} else {
					locatorResponse.setMessage("FAILED:Can not retrieve Country Info,this ip is not valid");
				}
			}else
			{
				locatorResponse.setMessage("FAILED:Can not retrieve Country Info,this ip is not valid");
			}	
		} catch (ParseException e) {
			e.printStackTrace();
			locatorResponse.setMessage("FAILED:Can not retrieve Country Info "+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			locatorResponse.setMessage("FAILED:Can not retrieve Country Info "+e.getMessage());
		} 
		return result;
	}

	public String getUrlService() {
		return urlService;
	}

	public void setUrlService(String urlService) {
		this.urlService = urlService;
	}

}
