package com.spocket.assignment.searchapi.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spocket.assignment.searchapi.dao.Product;
import com.spocket.assignment.searchapi.repositories.IProductRepository;
import com.spocket.assignment.searchapi.services.ProductService;


@SpringBootTest
public class ProductControllerTest {

	@Autowired
	private ProductService service;

	@MockBean
	private IProductRepository repository;


	@Test
	public void getAllProductsTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Product("786ec87f-c493-4aa8-a42b-99cac4e9209", 
								"Reusable Seal Silicone Food Fresh Bag", 1067, "$10.67 USD",
								"$21.34 USD","https://d2nxps5jx3f309.cloudfront.net/listing_images/attachments/0c6/b13/31-/card/DIDIHOU-1PC-Reusable-Seal-Silicone-Food-Fresh-Bag-Vacuum-Sealer-Fruit-Meat-Milk-Storage-Bags-Kitchen.jpg",
								"Home & Garde","China",true,"Orange Perses"),
						new Product("7eefda13-0f5b-4744-b244-12f86b17e47", 
								"Anti Scrape Furniture Leg Socks", 424, "$4.24 USD",
								"$8.48 USD","https://d2nxps5jx3f309.cloudfront.net/listing_images/attachments/194/c93/3f-/card/4pcs-lot-Anti-Slip-Mat-Bumper-Damper-Cute-Furniture-Leg-Feet-Rug-Caps-Felt-Pads-Cat.jpg",
								"Furniture","China",true,"Gold Atalanta")).collect(Collectors.toList()));
		assertEquals(2, service.getAllProducts().size());
	}

}
