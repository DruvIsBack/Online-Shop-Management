package com.osm.persistences;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.osm.persistences.User;

@Entity
@Table
public class UserType{

	@Id
	@GeneratedValue
	private long id;
	private String name;

	@OneToMany(mappedBy = "userType")
	private List<User> user;

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

	public List<User> getUser() {
	    return user;
	}

	public void setUser(List<User> param) {
	    this.user = param;
	}
}