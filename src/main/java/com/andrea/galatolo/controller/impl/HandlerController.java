package com.andrea.galatolo.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.andrea.galatolo.controller.inter.IHandlerController;
import com.andrea.galatolo.service.inter.IHandlerService;

@RestController
public class HandlerController implements IHandlerController {

	@Autowired
	IHandlerService service;
	
	@Override
	@RequestMapping(value =  "/json-maker", method = RequestMethod.GET)
	public Map<String,String> JSONMakerCall(@RequestBody Map<String, String> body) {
		Map<String,String> response = new HashMap<>();
		try {
			response.put("body", service.JSONHandler(body.get("customBody") ));
			response.put("200", "Success");
			return response;
		}catch (Exception e) {
			response.put("404", e.getMessage());
			e.printStackTrace();
			return response;
		} 
		
		
		
	}

}
