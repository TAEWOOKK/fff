package com.ezen.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.ProductDAO;
import com.ezen.biz.dto.ProductVO;

import utils.Criteria;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDao;
	
	@Override
	public List<ProductVO> getNewProductList() {
		return productDao.getNewProductList();
	}

	@Override
	public List<ProductVO> getBestProductList() {
		return productDao.getBestProductList();
	}

	@Override
	public ProductVO getProduct(ProductVO vo) {
		return productDao.getProduct(vo);
	}

	@Override
	public List<ProductVO> getProductListByKind(String kind) {
		return productDao.getProductListByKind(kind);
	}
	
	/////////////////////////////////// 아래는 Admin 부분 ///////////////////////////////////

	@Override	//총 상품 목록 개수 조회
	public int countProductList(String name) {
		return productDao.countProductList(name);
	}

	@Override	//상품 목록 조회
	public List<ProductVO> getListProduct(String name) {
		return productDao.listProduct(name);
	}

	@Override	//상품 추가
	public void insertProduct(ProductVO vo) {
		productDao.insertProduct(vo);
	}

	@Override	//상품 수정
	public void updateProduct(ProductVO vo) {
		productDao.updateProduct(vo);
	}

	@Override	//페이지별 상품 목록 조회
	public List<ProductVO> getListProductWithPaging(Criteria criteria, String name) {
		return productDao.listProductWithPaging(criteria, name);
	}

}
