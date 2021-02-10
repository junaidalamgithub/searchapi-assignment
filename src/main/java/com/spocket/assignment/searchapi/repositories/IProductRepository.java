package com.spocket.assignment.searchapi.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spocket.assignment.searchapi.dao.Product;

public interface IProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

	List<Product> findByPrice(int price);
	
	@Query(value = "SELECT * FROM products p WHERE LOWER(p.category_name) LIKE LOWER('%' || :category || '%')", nativeQuery = true)
	List<Product> findByCategoryName(@Param("category") String category);
	
	@Query(value = "SELECT * FROM products p WHERE LOWER(p.country_origin) LIKE LOWER('%' || :country || '%')", nativeQuery = true)
	List<Product> findByCountryOrigin(@Param("country") String country);
	
	@Query(value = "SELECT * FROM products p WHERE LOWER(p.supplier_name) LIKE LOWER('%' || :supplier || '%')", nativeQuery = true)
	List<Product> findBySupplierName(@Param("supplier") String supplier);
	
	@Query(value = "SELECT * FROM products p WHERE LOWER(p.title) LIKE LOWER('%' || :title || '%')", nativeQuery = true)
    List<Product> findByTitle(@Param("title") String title);
	
	List<Product> findByPremium(boolean isPremium);
}
