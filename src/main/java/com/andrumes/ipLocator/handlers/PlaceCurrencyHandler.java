package com.andrumes.ipLocator.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.andrumes.ipLocator.model.CurrencyByCountry;
import com.andrumes.ipLocator.model.LocatorResponse;
import com.andrumes.ipLocator.model.utils.CurrencyByCountryEspecificationBuilder;
import com.andrumes.ipLocator.repository.ICurrencyByCountryRepository;

public class PlaceCurrencyHandler extends LocatorHandler {

	private ICurrencyByCountryRepository currencyByCountryRepository;

	public PlaceCurrencyHandler() {
		// TODO Auto-generated constructor stub
	}

	public ICurrencyByCountryRepository getCurrencyByCountryRepository() {
		return currencyByCountryRepository;
	}

	public void setCurrencyByCountryRepository(ICurrencyByCountryRepository currencyByCountryRepository) {
		this.currencyByCountryRepository = currencyByCountryRepository;
	}

	@Override
	public boolean process(LocatorResponse locatorResponse) {
		boolean result = false;
		String contryCode = locatorResponse.getIsoCountryCode();
		CurrencyByCountryEspecificationBuilder builder = new CurrencyByCountryEspecificationBuilder();
		builder.with("countryCode", ":", contryCode);
		Specification<CurrencyByCountry> spec = builder.build();
		List<CurrencyByCountry> countrySelected = new ArrayList<CurrencyByCountry>();
		this.currencyByCountryRepository.findAll(spec).forEach(countrySelected::add);
		if (countrySelected.size() > 0) {
			result = true;
			locatorResponse.setBaseCurrency(countrySelected.get(0).getCode());
			locatorResponse.setMessage("ACCEPTED:currency Info retrieved");
			if (this.getNextHandler() != null) {
				result = this.getNextHandler().process(locatorResponse);
			}
		} else {
			locatorResponse.setMessage("FAILED:Can not retrieve currency Info, there is not information about this country");
		}
		return result;
	}

}
