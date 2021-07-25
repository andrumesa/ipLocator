package com.andrumes.ipLocator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.andrumes.ipLocator.model.LocatorRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
public class IpInfoControllerTest {
	
	 @Autowired
	 private MockMvc mvc;

	@Test
	public void  retrieveIpInfo_thenStatus200()
	  throws Exception {		
		ObjectMapper objectMapper = new ObjectMapper();
		LocatorRequest request=new LocatorRequest();
		request.setIp("5.2.69.50");
		request.setContent("!");
		String json =objectMapper.writeValueAsString(request);
        this.mvc.perform(post("/ipInfo") .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(MockMvcResultHandlers.print()).andExpect(status().is(202));
	}
}
