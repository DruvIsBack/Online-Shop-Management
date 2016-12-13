package com.osm.persistences;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.osm.persistences.Product;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class ProductVendor{
	@Id
	@GeneratedValue
	private long id;
	private String name;
	@ManyToOne
	private Product product;
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

	public Product getProduct() {
	    return product;
	}

	public void setProduct(Product param) {
	    this.product = param;
	}
}