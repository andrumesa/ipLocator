package com.andrumes.ipLocator.model.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.andrumes.ipLocator.model.CurrencyByCountry;

public class CurrencyByCountryEspecificationBuilder {
	 
	private final List<SearchCriteria> params;
	 
	    public CurrencyByCountryEspecificationBuilder() {
	        params = new ArrayList<SearchCriteria>();
	    }
	 
	    public CurrencyByCountryEspecificationBuilder with(String key, String operation, String value) {
	        params.add(new SearchCriteria(key, operation, value));
	        return this;
	    }
	 
	    public Specification<CurrencyByCountry> build() {
	        if (params.size() == 0) {
	            return null;
	        }
	 
			List<CurrencyByCountryEspecification> specs = params.stream()
	          .map(CurrencyByCountryEspecification::new)
	          .collect(Collectors.toList());
	         
			Specification<CurrencyByCountry> result = specs.get(0);
	 
	        for (int i = 1; i < params.size(); i++) {
	            result = params.get(i)
	              .isOrPredicate()
	                ? Specification.where(result)
	                  .or(specs.get(i))
	                : Specification.where(result)
	                  .and(specs.get(i));
	        }       
	        return result;
	    }
}
