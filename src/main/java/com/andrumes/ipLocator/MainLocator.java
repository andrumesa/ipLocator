package com.andrumes.ipLocator;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

import com.andrumes.ipLocator.handlers.IpProcessor;
import com.andrumes.ipLocator.model.BannedIp;
import com.andrumes.ipLocator.model.IpInfo;
import com.andrumes.ipLocator.repository.ICurrencyByCountryRepository;
import com.andrumes.ipLocator.repository.IbannedIpRepository;


@SpringBootApplication
@RestController
public class MainLocator {

	private static Logger logger = LogManager.getLogger(MainLocator.class);

	@Autowired
	private Environment env;

	@Autowired
	private IbannedIpRepository ibannedIpRepository;

	@Autowired
	private ICurrencyByCountryRepository currencyByCountryRepository;

	@Bean
	public IpProcessor getProcessor() {
		logger.info("..Creating ipProcessor");
		IpProcessor ipProcessor = new IpProcessor(env.getProperty("ip2country.url"), env.getProperty("fixer.url"),
				env.getProperty("fixer.token"), currencyByCountryRepository);
		return ipProcessor;
	}

	@Bean
	public Map<String, IpInfo> getIprequestMap() {
		Map<String, IpInfo> requestMap = new HashMap<>();
		try {
			logger.info("..Loading banned ips");
			ibannedIpRepository.findAll().forEach((BannedIp bIp) -> {
				requestMap.put(bIp.getIp(), new IpInfo(bIp.getIp()));
			});
		} catch (Exception e) {
			logger.error("..Loading banned ips",e.getMessage());
			e.printStackTrace();
		}
		return requestMap;
	}


	public static void main(String[] args) {
		SpringApplication.run(MainLocator.class, args);
		logger.info("..Starting ipLocator");
	}

}
