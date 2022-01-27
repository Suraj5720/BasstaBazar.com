package com.ecommerce.web.GlobalData;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.web.Model.Product;

public class GlobalData {

	public static List<Product> cart;
	static {
		
		cart = new ArrayList<Product>();
	}
}
