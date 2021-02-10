package com.spocket.assignment.searchapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spocket.assignment.searchapi.dao.Product;
import com.spocket.assignment.searchapi.services.ProductService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/spocket/api/v1/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@ApiOperation(value = "Gets all the products")
	@GetMapping("/all")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@ApiOperation(value = "Populate the Database through product JSON", notes = "Populates the DB by fething the response JSON from the products json url. The internet connectivity is required during this operation")
	@PostMapping("/populate-db")
	public void populateDB() {
		productService.populateDB();
	}

	@ApiOperation(value = "Gets all the products by ID", notes = "provide an id of the product to search for")
	@GetMapping("/{id}")
	public Optional<Product> getProductById(@PathVariable String id) {
		return productService.getProductById(id);
	}

	@ApiOperation(value = "Gets the products by provided search criterias", notes = "Searches alist of products based on different set of criterias like country, title, category, premium, price range and also has pagination options if the start pageNumber and pageSize are provided(pageSize needs to be greater than zero."
			+ " All parameters are optional and searches based on only the provided search parameters. "
			+ "If no parameter is provided then it fetches all the products.")
	@RequestMapping("/search")
	public List<Product> findByLikeAndBetweenCriteria(@RequestParam(required = false) String country,
			@RequestParam(required = false) String category, @RequestParam(required = false) String title,
			@RequestParam(required = false) String supplier, @RequestParam(required = false) String premium,
			@RequestParam(required = false) Integer price, @RequestParam(required = false) Integer fromPrice,
			@RequestParam(required = false) Integer toPrice, @RequestParam(required = false) Integer pageNumber,
			@RequestParam(required = false) Integer pageSize) {

		if (pageNumber != null && pageNumber.intValue() >= 0 && pageSize != null && pageSize.intValue() > 0) {
			Pageable pageable = PageRequest.of(pageNumber.intValue(), pageSize.intValue());// new QPageRequest(0,12);
			return productService.findByPagingCriteria(country, category, title, supplier, premium, price, fromPrice,
					toPrice, pageable);
		} else {
			return productService.findProducts(country, category, title, supplier, premium, price, fromPrice, toPrice);
		}
	}

}
