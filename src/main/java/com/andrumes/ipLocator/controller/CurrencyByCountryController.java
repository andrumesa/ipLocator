package com.andrumes.ipLocator.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrumes.ipLocator.model.CurrencyByCountry;
import com.andrumes.ipLocator.repository.ICurrencyByCountryRepository;


@RestController
public class CurrencyByCountryController {
	
	 @Autowired
	 private  ICurrencyByCountryRepository  currencyByCountryRepository;
	 
	 private static Logger logger = LogManager.getLogger(CurrencyByCountryController.class);

	 
	 @GetMapping("/currencyByCountry")
	 public ResponseEntity<List<CurrencyByCountry>> getPerson()
	 {
		 List<CurrencyByCountry> currencyByCountryList = new ArrayList<CurrencyByCountry>();
		 try {
			 currencyByCountryRepository.findAll().forEach(currencyByCountryList::add);			
			} catch (Exception e) {
				logger.error(" Error retrievng currencyByCountry data",e.getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		 return new ResponseEntity<>(currencyByCountryList, HttpStatus.OK);
	 }
}
