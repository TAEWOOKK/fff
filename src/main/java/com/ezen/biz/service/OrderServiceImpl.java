package com.ezen.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.AdminDAO;
import com.ezen.biz.dao.CartDAO;
import com.ezen.biz.dao.OrderDAO;
import com.ezen.biz.dto.CartVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.dto.SalesQuantity;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDAO orderDao;
	@Autowired
	private CartService cartService;
	@Autowired
	private CartDAO cartDao;

	@Override
	public int selectMaxOseq() {
		return orderDao.selectMaxOseq();
	}

	@Override
	public int insertOrder(OrderVO vo) {
		// 1) 신규 주문 번호 생성
		int oseq = selectMaxOseq();
		vo.setOseq(oseq);
		
		// 2) 신규 주문을 주문 테이블에 저장
		orderDao.insertOrder(vo);
		
		// 3) 장바구니 목록을 읽어 order_detail 테이블에 저장
		//먼저 장바구니 목록 가져온다 - 상단에 private CartService cartService; 의존성 주입
		List<CartVO> cartList = cartService.listCart(vo.getId());
		
		//처리하는 방식: OrderVO에 필요한 정보를 cart 정보에서 가져와서 OrderVO에 설정
		for (CartVO cart : cartList) {		
			OrderVO order = new OrderVO();
			
			//cart에서 가져와서 order에 넣어준다
			order.setOseq(oseq);
			order.setPseq(cart.getPseq());
			order.setQuantity(cart.getQuantity());
			
			insertOrderDetail(order);
			
			/*//에러 화면 테스트를 위한 에러 발생 코드
			if(true)
				throw new IllegalArgumentException("장바구니 데이터를 수정할 수 없습니다.");
			*/
			
			// 장바구니 테이블 업데이트(주문처리 결과 업데이트)
			cartService.updateCart(cart.getCseq());
		}
		return oseq;
	}

	@Override
	public void insertOrderDetail(OrderVO vo) {
		orderDao.insertOrderDetail(vo);	
	}

	@Override
	public List<OrderVO> getListOrderById(OrderVO vo) {
		return orderDao.listOrderById(vo);
	}

	@Override
	public List<Integer> getSeqOrdering(OrderVO vo) {
		return orderDao.getSeqOrdering(vo);
	}

	////////////////////////////아래는 관리자 영역////////////////////////////
	
	@Override
	public List<OrderVO> getListOrder(String mname) {
		return orderDao.listOrder(mname);
	}

	@Override
	public void updateOrderResult(int odseq) {
		orderDao.updateOrderResult(odseq);
	}

	@Override
	public List<SalesQuantity> getProductSales() {
		return orderDao.getProductSales();
	}
}