package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.ProductVO;

import utils.Criteria;

public interface ProductService {

	List<ProductVO> getNewProductList();

	List<ProductVO> getBestProductList();

	ProductVO getProduct(ProductVO vo);

	List<ProductVO> getProductListByKind(String kind);
	
	 /////////////////////////////////// 아래는 Admin 부분 ///////////////////////////////////
	
	//총 상품 목록 개수 조회
	int countProductList(String name);
		
	//상품 목록 조회
	List<ProductVO> getListProduct(String name);
	
	//상품 추가
	void insertProduct(ProductVO vo);
	
	//상품 수정
	void updateProduct(ProductVO vo);
	
	//페이지별 상품 목록 조회
	List<ProductVO> getListProductWithPaging(Criteria criteria, String name);


}