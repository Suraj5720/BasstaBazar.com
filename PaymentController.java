package com.ecommerce.web.Controller;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecommerce.web.GlobalData.GlobalData;
import com.razorpay.*;

@Controller
public class PaymentController {

	//creating order for payment
	@PostMapping("/payNow")
	@ResponseBody
	public String createOrder(@RequestBody Map<String,Object> data,Model model)throws Exception {
		model.addAttribute("cartCount",GlobalData.cart.size());
		System.out.println("order function chala");
		System.out.println(data);
		
		//convert string amount to int
		int amt =Integer.parseInt(data.get("amount").toString());
		var client = new RazorpayClient("rzp_test_kcwAscX6qePXga","hOc2jJ9cOAiWjLlfaXBzU3Uw"); 
		
		//create order
		JSONObject ob = new JSONObject();
		ob.put("amount", amt);
		ob.put("currency", "INR");
		ob.put("receipt","txn_123456789");
		
		Order order = client.Orders.create(ob);
		System.out.println(order);
		
		//save order data to db
		
		return order.toString();
	}
	
	//order placed page
	@GetMapping("/orderPlaced")
	public String getOrderPlaced(Model model) {
		
		try {
			
			int i = GlobalData.cart.size();
			while(i>0) {
				int a=0;
				GlobalData.cart.remove(a);
				a++;
				i++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("cartCount",GlobalData.cart.size());
		return"orderPlaced";
	}
	
	
}
