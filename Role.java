package com.ecommerce.web.Model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	@Column(nullable = false,unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "role")
	private List<Users> user;

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Role(int id, @NotEmpty String name, List<Users> user) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Users> getUser() {
		return user;
	}

	public void setUser(List<Users> user) {
		this.user = user;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
