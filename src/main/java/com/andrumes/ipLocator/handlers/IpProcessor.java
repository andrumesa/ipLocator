package com.andrumes.ipLocator.handlers;

import com.andrumes.ipLocator.model.LocatorRequest;
import com.andrumes.ipLocator.model.LocatorResponse;
import com.andrumes.ipLocator.repository.ICurrencyByCountryRepository;


/**
 * @author andru
 * chain of responsibility to process the ip information
 */
public class IpProcessor {

	private PlaceInfoHandler placeInfoHandler;
	private RatesHandler ratesHandler;
	private PlaceCurrencyHandler placeCurrencyHandler;

	public IpProcessor(String urlIp2country,String urlFixer,String tokenFixer,ICurrencyByCountryRepository  currencyByCountryRepository) {
		this.placeInfoHandler = new PlaceInfoHandler(urlIp2country);
		this.ratesHandler = new RatesHandler(urlFixer,tokenFixer);
		this.placeCurrencyHandler=new PlaceCurrencyHandler();
		placeCurrencyHandler.setCurrencyByCountryRepository(currencyByCountryRepository);
		this.createProcessorFlow();
	}

	public void createProcessorFlow() {
		this.placeInfoHandler.setNextHandler(this.placeCurrencyHandler);
		this.placeCurrencyHandler.setNextHandler(this.ratesHandler);
		// add more handlers as you need 
	}

	public LocatorResponse processIp(LocatorRequest locatorRequest) {
		LocatorResponse response = new LocatorResponse(locatorRequest);
		boolean result = placeInfoHandler.process(response);
		response.setResult(result);
		return response;
	}

}
