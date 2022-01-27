package com.ecommerce.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.web.GlobalData.GlobalData;
import com.ecommerce.web.Model.Product;
import com.ecommerce.web.Service.ProductService;

@Controller
public class CartController {

	@Autowired
	ProductService productService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCartPage(@PathVariable int id) {
	
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}
	@GetMapping("/cart")
	public String getCart(Model model){
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart",GlobalData.cart);
		return "cart";
	}
	//remove item
	@GetMapping("/cart/removeItem/{index}")
	public String removeCartItem(@PathVariable int index) {
		
		GlobalData.cart.remove(index);
		
		return "redirect:/cart";
	}
	//checkout
	@GetMapping("/checkout")
	public String getCheckout(Model model) {
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
