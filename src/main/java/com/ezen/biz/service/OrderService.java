package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.dto.SalesQuantity;

public interface OrderService {

	int selectMaxOseq();

	int insertOrder(OrderVO vo);

	void insertOrderDetail(OrderVO vo);
	
	List<OrderVO> getListOrderById(OrderVO vo);

	List<Integer> getSeqOrdering(OrderVO vo);
	
	////////////////////////////아래는 관리자 영역////////////////////////////
		
	// 주문 전체 조회 매핑
	List<OrderVO> getListOrder(String mname);
	
	// 주문 상태 갱신 매핑
	void updateOrderResult(int odseq);
	
	List<SalesQuantity> getProductSales();
}