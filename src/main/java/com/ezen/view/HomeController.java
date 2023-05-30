package com.ezen.view;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.service.ProductService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String home(Model model) {
		//신상품 조회
		List<ProductVO> newproductList = productService.getNewProductList();
		
		//베스트
		List<ProductVO> bestProductList = productService.getBestProductList();
		
		model.addAttribute("newProductList", newproductList);
		model.addAttribute("bestProductList", bestProductList);
		
		return "index";
	}
	
}
