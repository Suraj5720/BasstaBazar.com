package com.ecommerce.web.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.web.Model.Users;

public interface UserRepository extends  JpaRepository<Users, Integer> {
	Optional<Users> findUserByEmail(String email);
}
