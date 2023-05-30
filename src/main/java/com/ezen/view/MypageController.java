package com.ezen.view;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.biz.dto.CartVO;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.service.CartService;
import com.ezen.biz.service.OrderService;

@Controller
public class MypageController {
	

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;

	@PostMapping("/cart_insert")//url
	public String insertCart(CartVO vo, HttpSession session) {
		//로그인이 되어있는지 확인 - 세션에서 사용자 정보를 가져올 것.(멤버 컨트롤러에서@SessionAttributes("loginUser")되었는지 확인)
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		//비 로그인 시 로그인 화면으로.
		if(loginUser == null) {
			return "member/login";
		}else {
			//로그인 상태일 경우, 회원 ID를 설정하고 장바구니에 insert 수행
			vo.setId(loginUser.getId());
			
			cartService.insertCart(vo);
			
			return "redirect:cart_list";//url도 같이 요청
		}

		}
	
	@GetMapping("/cart_list")
	public String listCart(HttpSession session, Model model) {
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		//비 로그인 시 로그인 화면으로.
		if(loginUser == null) {
			return "member/login";
		}else {

			//로그인이 되어있으면 cartService의 장바구니 목록 실행
			List<CartVO> cartList = cartService.listCart(loginUser.getId());
			//장바구니 총액 계산
			int totalAmount=0;
			for(CartVO vo : cartList) {
				totalAmount += vo.getQuantity() * vo.getPrice2();
			}
			
		//model객체에 데이터 저장하여 jsp에 전달
			model.addAttribute("cartList", cartList);
			model.addAttribute("totalPrice", totalAmount);
			
			return "mypage/cartList";
		}
		
	}
	
	@PostMapping("/cart_delete")
	public String cartDelete(@RequestParam(value="cseq")int[] cseq) {
		for(int i=0; i<cseq.length; i++){
				System.out.println(cseq[i]);
				cartService.deleteCart(cseq[i]);
	}
		return "redirect:cart_list";
	}
	
	@PostMapping("/order_insert")
	public String orderInsert(HttpSession session, OrderVO order, RedirectAttributes reAttr) {
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		//비 로그인 시 로그인 화면으로.
		if(loginUser == null) {
			return "member/login";
		}else { 
			//OrderVO객체에 id저장
			order.setId(loginUser.getId());
			
			int oseq = orderService.insertOrder(order);
			
			//주문확인 화면에 표시하기 위해 주문번호(oseq) 전달
			//addAttribute는 GET, 새로고침을 해도 유지됨.
			//addFlashAttributesms Post, 새로고침을 해도 유지되지 않음.
			reAttr.addAttribute("oseq", oseq);
			
			return "redirect:order_list";
		}

	}
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
	//주문 처리 화면 표시
	@GetMapping("/order_list")
	public String orderListView(HttpSession session, OrderVO vo, Model model) {
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		//비 로그인 시 로그인 화면으로.
		if(loginUser == null) {
			return "member/login";
		}else {
			//oseq는 insert_order에서 전달되 데이터가 command객체에 저장되어 있음
			vo.setId(loginUser.getId());
			vo.setResult("1");//미처리 결과만 조회
			
			//로그인 한 회원의 방금 전 주문 내역으로 조회
			List<OrderVO> orderList = orderService.getListOrderById(vo);
			
			//주문 총 액 계산
			int totalAmount=0;
			for(OrderVO order : orderList) {
				totalAmount += order.getQuantity() * order.getPrice2();
			}
			
			
			//화면에 표시할 데이터 저장
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalAmount);
			orderService.getListOrderById(vo);
			
			return "mypage/orderList";
		}
	}
	
	//진행중인 주문 내역 요청 처리
	@GetMapping("/mypage")
	public String mypageView(HttpSession session, OrderVO vo, Model model) {
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "mypage/mypage";
	}else {
		

		//(1) 진행중인 주문 번호 목록 조회
		vo.setId(loginUser.getId());
		vo.setResult("1");
		List<Integer> oseqList = orderService.getSeqOrdering(vo);
		
		//(2) 위의 각 주문번호에 대해 주문번호를 조회하고 요약정보 생성
		List<OrderVO> summaryList = new ArrayList<>();//주문정보 저장 목록
		
		for(int oseq : oseqList) {
			//(2-1) 각 주문번호의 주문내역 조회 
			OrderVO order = new OrderVO();
			
			order.setId(loginUser.getId());
			order.setOseq(oseq);
			order.setResult("1");
			//주문 번호별 주문 목록
			List<OrderVO> orderList = orderService.getListOrderById(order);
			
			//(2-2)각 주문 요약 정보 생성
			OrderVO summary = new OrderVO();//요약 정보 저장 변수
			summary.setOseq(orderList.get(0).getOseq());//첫번째 요소 주문번호 저장
			summary.setIndate(orderList.get(0).getIndate());
			
			//상품명 요약정보 생성
			if (orderList.size() >= 2) {
				summary.setPname(orderList.get(0).getPname() + "외" + (orderList.size()-1) + "건");
			}else {//주문상품이 하나일 경우.
				summary.setPname(orderList.get(0).getPname());
			}
			//각 주문별 합계 금액
			int amount=0;
			for (OrderVO item : orderList) {
				amount += item.getQuantity() * item.getPrice2();
			}
			summary.setPrice2(amount);//합계금액 저장
			
			summaryList.add(summary);//각 주문요약 정보를 리스트에 추가
			
		}
		
		//(3) 화면에 전달한 데이터를 저장
		model.addAttribute("orderList", summaryList);
		model.addAttribute("title", "진행중인 주문내역");
		
		
	}
		return "mypage/mypage";
	}
	
	
	@GetMapping("/order_detail")
	public String orderDetailView(OrderVO vo, HttpSession session, Model model) {
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		//비 로그인 시 로그인 화면으로.
		if(loginUser == null) {
			return "member/login";
		}else {
			vo.setId(loginUser.getId());
			vo.setResult("");//처리, 미처리 모두 조회
			// (1) 주문번호에 대한 주문목록 조회
			List<OrderVO> orderList = orderService.getListOrderById(vo);
			// (2) 주문자 정보 생성
			OrderVO orderDetail = new OrderVO();
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setMname(orderList.get(0).getMname());
			
			// (3) 주문총액 계산
			int amount=0;
			for (OrderVO item : orderList) {
				amount += item.getQuantity() * item.getPrice2();
			}

			// (4) 화면에 표시할 정보 저장
			model.addAttribute("orderList", orderList);
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("totalPrice", amount);
			model.addAttribute("title", "주문 상세 정보");
			
			// (5) mypage 화면 호출
			return "mypage/orderDetail";
		}
	}
	
	//총 주문목록
	@GetMapping("/order_all")
	public String orderAllView(OrderVO vo,  HttpSession session, Model model) {
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "mypage/mypage";
	}else {
		

		//(1) 진행중인 주문 번호 목록 조회
		vo.setId(loginUser.getId());
		vo.setResult("");//처리, 미처리 모두 조회
		List<Integer> oseqList = orderService.getSeqOrdering(vo);
		
		//(2) 위의 각 주문번호에 대해 주문번호를 조회하고 요약정보 생성
		List<OrderVO> summaryList = new ArrayList<>();//주문정보 저장 목록
		
		for(int oseq : oseqList) {
			//(2-1) 각 주문번호의 주문내역 조회 
			OrderVO order = new OrderVO();
			
			order.setId(loginUser.getId());
			order.setOseq(oseq);
			order.setResult("");//처리, 미처리
			//주문 번호별 주문 목록
			List<OrderVO> orderList = orderService.getListOrderById(order);
			
			//(2-2)각 주문 요약 정보 생성
			OrderVO summary = new OrderVO();//요약 정보 저장 변수
			summary.setOseq(orderList.get(0).getOseq());//첫번째 요소 주문번호 저장
			summary.setIndate(orderList.get(0).getIndate());
			
			//상품명 요약정보 생성
			if (orderList.size() >= 2) {
				summary.setPname(orderList.get(0).getPname() + "외" + (orderList.size()-1) + "건");
			}else {//주문상품이 하나일 경우.
				summary.setPname(orderList.get(0).getPname());
			}
			//각 주문별 합계 금액
			int amount=0;
			for (OrderVO item : orderList) {
				amount += item.getQuantity() * item.getPrice2();
			}
			summary.setPrice2(amount);//합계금액 저장
			
			summaryList.add(summary);//각 주문요약 정보를 리스트에 추가
			
		}
		
		//(3) 화면에 전달한 데이터를 저장
		model.addAttribute("orderList", summaryList);
		model.addAttribute("title", "MY PAGE(총 주문내역)");
		
		
	}
		return "mypage/mypage";
		
		
	}
	
}
