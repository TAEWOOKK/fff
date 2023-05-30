package com.ezen.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.service.ProductService;

@Controller
public class ProductController {
//리퀘스트 + get방식 진행 가능
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/product_detail")
	public String productDetailAction(ProductVO vo, Model model) {
		
	//상품 상세조회
	ProductVO product = productService.getProduct(vo);
	
	//JSP에 결과 전달
	model.addAttribute("productVO", product);
	return "product/productDetail";
	}
	@GetMapping(value="/category")
	public String productKindAction(ProductVO vo, Model model) {
		List<ProductVO> kindList = productService.getProductListByKind(vo.getKind());
				
		model.addAttribute("productKindList", kindList);
		return "product/productKind";
				
	}
	
}
