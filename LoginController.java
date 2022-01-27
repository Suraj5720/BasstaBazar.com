package com.ecommerce.web.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ecommerce.web.GlobalData.GlobalData;
import com.ecommerce.web.Model.Role;
import com.ecommerce.web.Model.Users;
import com.ecommerce.web.Repository.RoleRepository;
import com.ecommerce.web.Repository.UserRepository;

@Controller
public class LoginController {
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String login() {
		//clear cart data when user logout 
		GlobalData.cart.clear();
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@PostMapping("/register")
	public String registerPost(@ModelAttribute("user")Users user,HttpServletRequest request)throws ServletException{
		String password = user.getPassword();
		//user.setPassword(bCryptPasswordEncoder.encode(password));
		user.setPassword(password);
		List<Role> role=new ArrayList<>();
		role.add(roleRepository.findById(2).get());
		user.setRole(role);
		userRepository.save(user);
		request.login(user.getEmail(), password);
		return "redirect:/";
	}
	

}
