package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.CartVO;

@Repository
public class CartDAO{
	@Autowired
	private SqlSessionTemplate mybatis;
//장바구니 담기 
	public void insertCart(CartVO vo) {
		mybatis.insert("CartMapper.insertCart", vo);
	}
	//장바구니 목록
	public List<CartVO> listCart(String id) {
		return mybatis.selectList("CartMapper.listCart", id);		
	}
	//장바구니 취소
	public void deleteCart(int cseq) {
		mybatis.delete("CartMapper.deleteCart", cseq);
	}
	//장바구니 처리 결과 업데이트
	public void updateCart(int cseq) {
		mybatis.update("CartMapper.updateCart", cseq);
	}
	
}
