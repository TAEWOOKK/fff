package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.dto.SalesQuantity;

@Repository
public class OrderDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public int selectMaxOseq() {
		
		return mybatis.selectOne("OrderMapper.selectMaxOseq");
	}
	
	public void insertOrder(OrderVO vo) {

		mybatis.insert("OrderMapper.insertOrder", vo);
	}
	
	public void insertOrderDetail(OrderVO vo) {
		
		mybatis.insert("OrderMapper.insertOrderDetail", vo);
	}
	
	public List<OrderVO> listOrderById(OrderVO vo){
		
		return mybatis.selectList("OrderMapper.listOrderById", vo);
		
	}
	
	//�α����� ������� �������� �ֹ���ȣ ��� ��ȸ ='1'
	public List<Integer> getSeqOrdering(OrderVO vo){
		
		return mybatis.selectList("OrderMapper.getSeqOrdering", vo);
	}
	
	////////////////////////////�Ʒ��� ������ ����////////////////////////////
	
	// �ֹ� ��ü ��ȸ ����
	public List<OrderVO> listOrder(String mname) {
		return mybatis.selectList("OrderMapper.listOrder", mname);
	}
	
	// �ֹ� ���� ���� ����
	public void updateOrderResult(int odseq) {
		mybatis.update("OrderMapper.updateOrderResult", odseq);
	}
	
	// ��ǰ �Ǹ� ���� ��ȸ
	public List<SalesQuantity> getProductSales() {
		return mybatis.selectList("OrderMapper.listProductSales");
	}
}









