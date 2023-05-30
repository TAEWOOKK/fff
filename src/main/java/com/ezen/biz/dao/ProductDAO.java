package com.ezen.biz.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.dto.SalesQuantity;

import utils.Criteria;

@Repository
public class ProductDAO{
//"product-mapping.xml"���� ������ ȣ���ϴ� �޼ҵ� �ۼ�
	//�޼ҵ���� ���� ID�� ������.
	@Autowired
	SqlSessionTemplate mybatis;
	
	public List<ProductVO> getNewProductList() {
		return mybatis.selectList("ProductMapper.getNewProductList");
	}
	
	public List<ProductVO> getBestProductList() {
		return mybatis.selectList("ProductMapper.getBestProductList");
	}
	
	public ProductVO getProduct(ProductVO vo) {
		return mybatis.selectOne("ProductMapper.getProduct", vo);
	}
	
	public List<ProductVO> getProductListByKind(String kind) {
		return mybatis.selectList("ProductMapper.getProductListByKind",kind);
	}
	
	/////////////////////////////////// �Ʒ��� Admin �κ� ///////////////////////////////////
	
	//�� ��ǰ ��� ���� ��ȸ
	//�Ķ���� name : �˻��� ��ǰ��
	//�̸��� �������� ��ü ��ǰ ��Ͽ� ���� ��ǰ ���� ��ȸ(���� ""�� ��� ��ü ��ǰ ���� ��ȸ)
	public int countProductList(String name) {
		return mybatis.selectOne("ProductMapper.countProductList", name);
	}
	
	//��ǰ ��� ��ȸ
	public List<ProductVO> listProduct(String name) {
		return mybatis.selectList("ProductMapper.listProduct", name);
	}
	
	//�������� ��ǰ ��� ��ȸ
	//�Է� �Ķ���� 
	//	Criteria criteria : ���� ������ ����
	//	String name : ��ǰ�� �˻���
	public List<ProductVO> listProductWithPaging(Criteria criteria, String name) {
		Map<String, Object> map = new HashMap<>();
			map.put("criteria", criteria);
			map.put("name", name);
		return mybatis.selectList("ProductMapper.listProductWithPaging", map);
	}
	
	//��ǰ �߰�
	public void insertProduct(ProductVO vo) {
		mybatis.insert("ProductMapper.insertProduct", vo);
	}
	
	//��ǰ ����
	public void updateProduct(ProductVO vo) {
		mybatis.update("ProductMapper.updateProduct", vo);
	}
}
