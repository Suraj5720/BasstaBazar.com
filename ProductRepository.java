package com.ecommerce.web.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.web.Model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	 List<Product> findAllByCategoryId(int id);

}
