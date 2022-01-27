package com.ecommerce.web.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ecommerce.web.Model.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>{

}
