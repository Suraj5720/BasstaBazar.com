package com.ecommerce.web.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.web.Model.Category;
import com.ecommerce.web.Model.Product;
import com.ecommerce.web.Repository.ProductRepository;
import com.ecommerce.web.Service.CategoryService;
import com.ecommerce.web.Service.ProductService;
import com.ecommerce.web.dto.ProductDTO;

@Controller
public class AdminController {

	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	//C:\Users\suraj\Documents\workspace-spring-tool-suite-4-4.13.0.RELEASE\EcommerceWebSpring\src\main\resources\static\productImages
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping("/admin")
	public String adminHome() {
		
		return "adminHome";
	}
	@GetMapping("/admin/categories")
	public String getCategory(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	@GetMapping("/admin/categories/add")
	public String getCategoryAdd(Model model) {
		model.addAttribute("category",new Category());
		return "categoriesAdd";
	}
	@RequestMapping(path="/admin/categories/add",method = RequestMethod.POST)
	public String postCategoryAdd(@ModelAttribute("category")Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String removeCategoryById(@PathVariable("id")int id) {
		categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategoryById(@PathVariable("id")int id,Model model) {
		Optional<Category> category= categoryService.getCategoryById(id);
		if(category.isPresent()) {
			model.addAttribute("category",category);
			return "categoriesAdd";
		}
		else {
			return "404";
		}
		
	}
	// product section
	@GetMapping("/admin/products")
	public String getProduct(Model model) {
		model.addAttribute("products",productService.getAllproduct());
		return "products";
	}
	@GetMapping("/admin/products/add")
	public String getProductAdd(Model model) {
		model.addAttribute("productDTO",new ProductDTO());
		model.addAttribute("categories",categoryService.getAllCategory());
		return "productsAdd";
	}
	@PostMapping("/admin/products/add")	
	public String postProductAdd(@ModelAttribute("productDTO")ProductDTO productDTO,
			@RequestParam("productImage")MultipartFile file,
			@RequestParam("imgName")String imgName)throws IOException{
		
		//convert productdto object to product for saving to db
		Product product =new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		//image process break down image into two parts 1st image url and img name
		String imageUUID;
		//isempty() return false boolean if file exists not empty
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			//path name
			Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
			//actual file path
			Files.write(fileNameAndPath,file.getBytes());
		}else {
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);
		productService.addProduct(product);
		
		return "redirect:/admin/products";
	}
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProductById(@PathVariable("id")long id) {
		productService.removeProductById(id);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProductById(@PathVariable("id")long id,Model model) {
		//reverse engineering of change product to productdto
		Product product= productService.getProductById(id).get();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("productDTO",productDTO);
		
		return "productsAdd";
	}
	
}
