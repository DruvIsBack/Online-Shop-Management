package com.osm.persistences;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.osm.persistences.ProductVendor;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
@Table
public class Product {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String description;
	private Double price;
	private String pic;
	@ManyToOne
	private ProductSubCategory productSubCategory;
	@OneToMany(mappedBy = "product")
	private List<ProductVendor> productVendor;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String photo) {
		this.pic = photo;
	}
	public ProductSubCategory getProductSubCategory() {
	    return productSubCategory;
	}
	public void setProductSubCategory(ProductSubCategory param) {
	    this.productSubCategory = param;
	}
	public List<ProductVendor> getProductVendor() {
	    return productVendor;
	}
	public void setProductVendor(List<ProductVendor> param) {
	    this.productVendor = param;
	}
}
