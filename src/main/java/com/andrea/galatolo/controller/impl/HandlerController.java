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
	@RequestMapping(value =  "/json-maker", method = RequestMethod.POST)
	public Map<String,String> JSONMakerCall(@RequestBody Map<String, String> body) {
		Map<String,String> response = new HashMap<>();
		try {
			String req = body.get("customBody");
			if(req != null && !req.isEmpty()) {
				response.put("res-body", service.JSONHandler(req));
				response.put("200", "Success");
			}else {
				response.put("403", "no text");
			}
			response.put("customBody", req);
			return response;
		}catch (Exception e) {
			response.put("404", e.getMessage());
			e.printStackTrace();
			return response;
		} 
		
		
		
	}

}
