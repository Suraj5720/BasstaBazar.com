package com.ecommerce.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.web.GlobalData.GlobalData;
import com.ecommerce.web.Service.CategoryService;
import com.ecommerce.web.Service.ProductService;

@Controller
public class UserController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping({"/","/home"})
	public String userHome(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "index";
	}
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("products",productService.getAllproduct());
		return "shop";
	}
	@GetMapping("/shop/category/{id}")
	public String getProductByCategoryId(@PathVariable int id,Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("products",productService.getAllProductsByCategoryId(id));
		return "shop";
	}
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable int id,Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("product",productService.getProductById(id).get());
		return "viewProduct";
	}
	
}
