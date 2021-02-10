package com.spocket.assignment.searchapi.dao;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAlias;

@Entity
@Table(name="products")
public class Product {
	
	@Id
	private String id;
    private String title;
    private int price;
    @JsonAlias("formatted_price")
    private String formattedPrice;//formatted_price;
    @JsonAlias("formatted_msrp")
    private String formattedMsrp;//formatted_msrp;
    @JsonAlias("image_cover_url")
    private String imageCoverUrl;//image_cover_url;
    @JsonAlias("category_name")
    private String categoryName;//category_name;
    @JsonAlias("country_origin")
    private String countryOrigin;//country_origin;
    @Transient
    @JsonAlias("shipping_exclusions")
    private ArrayList<String> shippingExclusions = new ArrayList<String>(); //shipping_exclusions
    private boolean premium;
    @JsonAlias("supplier_name")
    private String supplierName;//supplier_name;
    
	public Product() {
		super();
	}
	
	

	public Product(String id, String title, int price, String formattedPrice, String formattedMsrp,
			String imageCoverUrl, String categoryName, String countryOrigin,
			boolean premium, String supplierName) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.formattedPrice = formattedPrice;
		this.formattedMsrp = formattedMsrp;
		this.imageCoverUrl = imageCoverUrl;
		this.categoryName = categoryName;
		this.countryOrigin = countryOrigin;
		this.premium = premium;
		this.supplierName = supplierName;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getFormattedPrice() {
		return formattedPrice;
	}

	public void setFormattedPrice(String formattedPrice) {
		this.formattedPrice = formattedPrice;
	}

	public String getFormattedMsrp() {
		return formattedMsrp;
	}

	public void setFormattedMsrp(String formattedMsrp) {
		this.formattedMsrp = formattedMsrp;
	}

	public String getImageCoverUrl() {
		return imageCoverUrl;
	}

	public void setImageCoverUrl(String imageCoverUrl) {
		this.imageCoverUrl = imageCoverUrl;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCountryOrigin() {
		return countryOrigin;
	}

	public void setCountryOrigin(String countryOrigin) {
		this.countryOrigin = countryOrigin;
	}

	public ArrayList<String> getShippingExclusions() {
		return shippingExclusions;
	}

	public void setShippingExclusions(ArrayList<String> shippingExclusions) {
		this.shippingExclusions = shippingExclusions;
	}

	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

}
