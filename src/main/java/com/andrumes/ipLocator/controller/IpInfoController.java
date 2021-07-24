package com.andrumes.ipLocator.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.andrumes.ipLocator.handlers.IpProcessor;
import com.andrumes.ipLocator.model.BannedIp;
import com.andrumes.ipLocator.model.IpInfo;
import com.andrumes.ipLocator.model.LocatorRequest;
import com.andrumes.ipLocator.model.LocatorResponse;
import com.andrumes.ipLocator.repository.IbannedIpRepository;

@RestController
public class IpInfoController {

	@Autowired
	public IpProcessor getProcessor;

	@Autowired
	public Map<String, IpInfo> getIprequestMap;
	
	@Autowired
	 private  IbannedIpRepository  ibannedIpRepository;
	
	@Autowired
	private Environment env;

	@PostMapping("/ipInfo")
	public ResponseEntity<LocatorResponse> retrieveIpInfo(@RequestBody LocatorRequest locatorRequest) {
		LocatorResponse locatorResponse= new LocatorResponse(locatorRequest);
		ResponseEntity<LocatorResponse> result ;
		if (locatorRequest.getIp() != null && !locatorRequest.getIp().equalsIgnoreCase("")) {
			 Boolean isBanned = this.validateBanByIp(locatorResponse);
			 if(isBanned)
			 {
				 result = new ResponseEntity<LocatorResponse>(locatorResponse, HttpStatus.TOO_MANY_REQUESTS);
			 }else
			 {
				 locatorResponse = getProcessor.processIp(locatorRequest);
				 result = new ResponseEntity<LocatorResponse>(locatorResponse, HttpStatus.ACCEPTED);
			 }
		}else
		{
			locatorResponse.setMessage("BAD_REQUEST:it is necessary an IP");
			result = new ResponseEntity<LocatorResponse>(locatorResponse, HttpStatus.BAD_REQUEST);
		}
		return result;
	}
	
	private Boolean validateBanByIp(LocatorResponse locatorResponse)
	{
		boolean isBaned=false;
		String currentIp=locatorResponse.getIp();
		if (getIprequestMap.containsKey(currentIp)) {
			//This ip is previously accessed
			IpInfo ipInfo = getIprequestMap.get(currentIp);
			isBaned=ipInfo.isBanned();
			//check if this ip is banned
			if(!isBaned)
			{
				long differenceInMilis=System.currentTimeMillis()- ipInfo.getTimeInmillis(); 			
				//check if this ip is in a range of banLimitTime of previously request
				Integer banLimitTime= Integer.parseInt(env.getProperty("ban.limitTime"));
				Integer hits= Integer.parseInt(env.getProperty("ban.hits"));
				if(differenceInMilis<banLimitTime)
			    {
					//If this ip in a the range add a hit  
			    	ipInfo.setHits(ipInfo.getHits()+1);	
			    	if(ipInfo.getHits()>=hits)
			    	{
			    		// if the hits reach limit ban
			    		isBaned=true;
			    		ipInfo.setBanned(isBaned);
			    	}
			    	BannedIp bannedIp=new  BannedIp(currentIp);
			    	//store in data base and in memory
			    	ibannedIpRepository.save(bannedIp);
			    	getIprequestMap.put(currentIp, ipInfo);
			    }else
			    {
			    	ipInfo.setTimeInmillis(System.currentTimeMillis());
			    	getIprequestMap.put(currentIp, ipInfo);
			    	// if the ip its outside of the range restart the time group
			    }
			}
		} else 
		{
			//is a  new IP
			getIprequestMap.put(currentIp, new IpInfo(currentIp, false));
		}
		if(!isBaned)
		{
			locatorResponse.setMessage("ACCEPTED:ip is not banned");
		}else
		{
			locatorResponse.setMessage("TOO_MANY_REQUESTS:too many request with this ip");
		}
		return isBaned;
	}

}
