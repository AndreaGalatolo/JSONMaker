package com.andrea.galatolo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.andrea.galatolo.service.inter.IHandlerService;

@Service
public class HandlerService implements IHandlerService {
	
	@Override
	public String JSONHandler(String input) {
		StringBuilder JSONBody = new StringBuilder().append("{");
		if(input != null && !input.isEmpty()) {
			String[] rows = input.split("\\n");
			Map<String,ArrayList<String>> JSONMap = orderRowsForFather(rows);
			if(JSONMap != null && !JSONMap.isEmpty())
				JSONbuild(JSONBody, JSONMap);
		}
		return JSONBody.append("}").toString();
	}

	private void JSONbuild(StringBuilder jSONBody, Map<String, ArrayList<String>> jSONMap) {
		ArrayList<String> jsonRows = new ArrayList<>();
		for (String father : jSONMap.keySet()) {
			jsonRows.add(father + 
					":{" + String.join(": string, ", jSONMap.get(father)) + ": string }");
		}
		if(!jsonRows.isEmpty()) jSONBody.append(String.join(", ", jsonRows));
		
	}

	private Map<String,ArrayList<String>> orderRowsForFather(String[] rows) {
		Map<String,ArrayList<String>> ret = new HashMap<String, ArrayList<String>>();
		if (rows != null && rows.length > 0) {
			for (String fatherRow : rows) {
				ArrayList<String> oggetto = new ArrayList<>();
				String[] array = fatherRow.split("\\.");
				String father = (Stream.of(array).
						collect(Collectors.toList())).get(0);
				List<String> childList = Stream.of(rows)
						.filter(e -> e.startsWith(father))
						.collect(Collectors.toList());
				if(!ret.containsKey(father)) {
					for (String childRow : childList) {
						oggetto.addAll(Stream.of(childRow.split("\\."))
								.filter(e -> !e.equals(father))
								.collect(Collectors.toList()));
					}
					ret.put(father, oggetto);
				}
			}
			
		}
		return ret;
	}
	
	
	

}
