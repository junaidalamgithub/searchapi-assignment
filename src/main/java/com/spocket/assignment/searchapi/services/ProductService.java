package com.spocket.assignment.searchapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spocket.assignment.searchapi.dao.Product;
import com.spocket.assignment.searchapi.repositories.IProductRepository;
import com.spocket.assignment.searchapi.util.Utils;

@Service
public class ProductService {

	@Autowired
	IProductRepository productRepository;

	private List<Product> products;

	public void populateDB() {
		products = Utils.getProductsFromJsonURL();
		for (Product product : products) {
			productRepository.save(product);
		}
		products = null;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById(String id) {
		return productRepository.findById(id);
	}

	public List<Product> findByPagingCriteria(String country, String category, String title, String supplier,
			String premium, Integer price, Integer fromPrice, Integer toPrice, Pageable pageable) {
		Page<Product> page = productRepository.findAll(new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (country != null) {
					predicates.add(
							criteriaBuilder.and(criteriaBuilder.like(root.get("countryOrigin"), "%" + country + "%")));
				}
				if (title != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("title"), "%" + title + "%")));
				}
				if (category != null) {
					predicates.add(
							criteriaBuilder.and(criteriaBuilder.like(root.get("categoryName"), "%" + category + "%")));
				}
				if (supplier != null) {
					predicates.add(
							criteriaBuilder.and(criteriaBuilder.like(root.get("supplierName"), "%" + supplier + "%")));
				}
				if (premium != null) {
					boolean isPremium = premium.toLowerCase().equals("true") ? true : false;
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("premium"), isPremium)));
				}
				if (price != null && price != 0) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("price"), price)));
				}
				if (fromPrice != null && toPrice != null && fromPrice != 0 && toPrice != 0) {
					predicates.add(criteriaBuilder.between(root.get("price"), fromPrice, toPrice));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);

		return page.getContent();
	}

	@SuppressWarnings("serial")
	public List<Product> findProducts(String country, String category, String title, String supplier, String premium,
			Integer price, Integer fromPrice, Integer toPrice) {
		return productRepository.findAll(new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if (country != null) {
					predicates.add(
							criteriaBuilder.and(criteriaBuilder.like(root.get("countryOrigin"), "%" + country + "%")));
				}
				if (title != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("title"), "%" + title + "%")));
				}
				if (category != null) {
					predicates.add(
							criteriaBuilder.and(criteriaBuilder.like(root.get("categoryName"), "%" + category + "%")));
				}
				if (supplier != null) {
					predicates.add(
							criteriaBuilder.and(criteriaBuilder.like(root.get("supplierName"), "%" + supplier + "%")));
				}
				if (premium != null) {
					boolean isPremium = premium.toLowerCase().equals("true") ? true : false;
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("premium"), isPremium)));
				}
				if (price != null && price != 0) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("price"), price)));
				}
				if (fromPrice != null && toPrice != null && fromPrice != 0 && toPrice != 0) {
					predicates.add(criteriaBuilder.between(root.get("price"), fromPrice, toPrice));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}
}
