package com.osm.persistences;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.osm.persistences.Product;
import com.osm.persistences.ProductCategory;

@Entity
@Table
public class ProductSubCategory{			//This is a product category
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String pic;
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long seq;

	@ManyToOne
	private ProductCategory productCategory;

	@OneToMany(mappedBy = "productSubCategory")
	private List<Product> product;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String param) {
		this.name = param;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String param) {
		this.pic = param;
	}

	public ProductCategory getProductCategory() {
	    return productCategory;
	}

	public void setProductCategory(ProductCategory param) {
	    this.productCategory = param;
	}

	public List<Product> getProduct() {
	    return product;
	}

	public void setProduct(List<Product> param) {
	    this.product = param;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}
}