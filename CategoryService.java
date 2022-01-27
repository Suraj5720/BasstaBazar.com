package com.ecommerce.web.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.web.Model.Category;
import com.ecommerce.web.Repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	public List<Category> getAllCategory(){
		Iterable<Category> list = categoryRepository.findAll();
		return (List<Category>) list;
	}
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void removeCategoryById(int id) {
		categoryRepository.deleteById(id);
	}
	public Optional<Category> getCategoryById(int id) {
		Optional<Category> category =  categoryRepository.findById(id);
		return category;
	}
}
