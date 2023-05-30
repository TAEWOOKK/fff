package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.CartVO;

public interface CartService {

	//��ٱ��� ��� 
	void insertCart(CartVO vo);

	//��ٱ��� ���
	List<CartVO> listCart(String id);

	//��ٱ��� ���
	void deleteCart(int cseq);
	
	//�ֹ� ������Ʈ
	public void updateCart(int cseq) ;
}