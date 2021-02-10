package com.spocket.assignment.searchapi.util;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spocket.assignment.searchapi.dao.Product;

public class Utils {
	
	
	public static List<Product> getProductsFromJsonURL() {
		 ObjectMapper mapper = new ObjectMapper();
		 List<Product> product = new ArrayList<>();
		try {
			product = mapper.readValue(new URL(Constants.PRODUCT_JSON_URL), new TypeReference<List<Product>>() {});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return product;
	}

}
