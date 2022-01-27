package com.ecommerce.web.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
@Entity
@Data
@Table(name="users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	@Column(nullable = false)
	private String firstName;
	
	private String lastName;
	
	@NotEmpty
	@Column(nullable=false,unique=true)
	@Email(message= "{errors.invalid_email")
	private String email;
	
	
	@Column(nullable = false)
	private String password;
	
	@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
				joinColumns= {@JoinColumn(name="USER_ID",referencedColumnName = "ID")},
				inverseJoinColumns= {@JoinColumn(name="ROLE_ID",referencedColumnName = "ID")}
			)
	private List<Role> role;
	
	public Users(int id, @NotEmpty String firstName, String lastName,
			@NotEmpty @Email(message = "{errors.invalid_email") String email, @NotEmpty String password,
			List<Role> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<Role> getRole() {
		return role;
	}


	public void setRole(List<Role> role) {
		this.role = role;
	}


	public Users(Users user) {
		
		this.id = user.getId();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.role = user.getRole();
	}
	
}
