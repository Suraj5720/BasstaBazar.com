package com.ecommerce.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.web.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	
}
