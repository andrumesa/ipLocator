package com.andrumes.ipLocator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.andrumes.ipLocator.model.CurrencyByCountry;

@Repository
public interface ICurrencyByCountryRepository extends  JpaRepository<CurrencyByCountry,Integer> ,JpaSpecificationExecutor<CurrencyByCountry> {
	
}
