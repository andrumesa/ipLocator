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

import com.andrumes.ipLocator.model.BannedIp;
import com.andrumes.ipLocator.repository.IbannedIpRepository;

@RestController
public class BannedIpController {
	
	private static Logger logger = LogManager.getLogger(BannedIpController.class);
	
	@Autowired
	 private  IbannedIpRepository  ibannedIpRepository;

	 @GetMapping("/bannedIp")
	 public ResponseEntity<List<BannedIp>> getPerson()
	 {
		 List<BannedIp> bannedIpList = new ArrayList<BannedIp>();
		 try {
			 ibannedIpRepository.findAll().forEach(bannedIpList::add);			
			} catch (Exception e) {
				logger.error("Error retrieving ip List",e.getMessage());
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		 return new ResponseEntity<>(bannedIpList, HttpStatus.OK);
	 }
	 
}
