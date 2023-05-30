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
		//�α����� �Ǿ��ִ��� Ȯ�� - ���ǿ��� ����� ������ ������ ��.(��� ��Ʈ�ѷ�����@SessionAttributes("loginUser")�Ǿ����� Ȯ��)
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		//�� �α��� �� �α��� ȭ������.
		if(loginUser == null) {
			return "member/login";
		}else {
			//�α��� ������ ���, ȸ�� ID�� �����ϰ� ��ٱ��Ͽ� insert ����
			vo.setId(loginUser.getId());
			
			cartService.insertCart(vo);
			
			return "redirect:cart_list";//url�� ���� ��û
		}

		}
	
	@GetMapping("/cart_list")
	public String listCart(HttpSession session, Model model) {
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		//�� �α��� �� �α��� ȭ������.
		if(loginUser == null) {
			return "member/login";
		}else {

			//�α����� �Ǿ������� cartService�� ��ٱ��� ��� ����
			List<CartVO> cartList = cartService.listCart(loginUser.getId());
			//��ٱ��� �Ѿ� ���
			int totalAmount=0;
			for(CartVO vo : cartList) {
				totalAmount += vo.getQuantity() * vo.getPrice2();
			}
			
		//model��ü�� ������ �����Ͽ� jsp�� ����
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
		//�� �α��� �� �α��� ȭ������.
		if(loginUser == null) {
			return "member/login";
		}else { 
			//OrderVO��ü�� id����
			order.setId(loginUser.getId());
			
			int oseq = orderService.insertOrder(order);
			
			//�ֹ�Ȯ�� ȭ�鿡 ǥ���ϱ� ���� �ֹ���ȣ(oseq) ����
			//addAttribute�� GET, ���ΰ�ħ�� �ص� ������.
			//addFlashAttributesms Post, ���ΰ�ħ�� �ص� �������� ����.
			reAttr.addAttribute("oseq", oseq);
			
			return "redirect:order_list";
		}

	}
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
	//�ֹ� ó�� ȭ�� ǥ��
	@GetMapping("/order_list")
	public String orderListView(HttpSession session, OrderVO vo, Model model) {
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		//�� �α��� �� �α��� ȭ������.
		if(loginUser == null) {
			return "member/login";
		}else {
			//oseq�� insert_order���� ���޵� �����Ͱ� command��ü�� ����Ǿ� ����
			vo.setId(loginUser.getId());
			vo.setResult("1");//��ó�� ����� ��ȸ
			
			//�α��� �� ȸ���� ��� �� �ֹ� �������� ��ȸ
			List<OrderVO> orderList = orderService.getListOrderById(vo);
			
			//�ֹ� �� �� ���
			int totalAmount=0;
			for(OrderVO order : orderList) {
				totalAmount += order.getQuantity() * order.getPrice2();
			}
			
			
			//ȭ�鿡 ǥ���� ������ ����
			model.addAttribute("orderList", orderList);
			model.addAttribute("totalPrice", totalAmount);
			orderService.getListOrderById(vo);
			
			return "mypage/orderList";
		}
	}
	
	//�������� �ֹ� ���� ��û ó��
	@GetMapping("/mypage")
	public String mypageView(HttpSession session, OrderVO vo, Model model) {
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "mypage/mypage";
	}else {
		

		//(1) �������� �ֹ� ��ȣ ��� ��ȸ
		vo.setId(loginUser.getId());
		vo.setResult("1");
		List<Integer> oseqList = orderService.getSeqOrdering(vo);
		
		//(2) ���� �� �ֹ���ȣ�� ���� �ֹ���ȣ�� ��ȸ�ϰ� ������� ����
		List<OrderVO> summaryList = new ArrayList<>();//�ֹ����� ���� ���
		
		for(int oseq : oseqList) {
			//(2-1) �� �ֹ���ȣ�� �ֹ����� ��ȸ 
			OrderVO order = new OrderVO();
			
			order.setId(loginUser.getId());
			order.setOseq(oseq);
			order.setResult("1");
			//�ֹ� ��ȣ�� �ֹ� ���
			List<OrderVO> orderList = orderService.getListOrderById(order);
			
			//(2-2)�� �ֹ� ��� ���� ����
			OrderVO summary = new OrderVO();//��� ���� ���� ����
			summary.setOseq(orderList.get(0).getOseq());//ù��° ��� �ֹ���ȣ ����
			summary.setIndate(orderList.get(0).getIndate());
			
			//��ǰ�� ������� ����
			if (orderList.size() >= 2) {
				summary.setPname(orderList.get(0).getPname() + "��" + (orderList.size()-1) + "��");
			}else {//�ֹ���ǰ�� �ϳ��� ���.
				summary.setPname(orderList.get(0).getPname());
			}
			//�� �ֹ��� �հ� �ݾ�
			int amount=0;
			for (OrderVO item : orderList) {
				amount += item.getQuantity() * item.getPrice2();
			}
			summary.setPrice2(amount);//�հ�ݾ� ����
			
			summaryList.add(summary);//�� �ֹ���� ������ ����Ʈ�� �߰�
			
		}
		
		//(3) ȭ�鿡 ������ �����͸� ����
		model.addAttribute("orderList", summaryList);
		model.addAttribute("title", "�������� �ֹ�����");
		
		
	}
		return "mypage/mypage";
	}
	
	
	@GetMapping("/order_detail")
	public String orderDetailView(OrderVO vo, HttpSession session, Model model) {
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		//�� �α��� �� �α��� ȭ������.
		if(loginUser == null) {
			return "member/login";
		}else {
			vo.setId(loginUser.getId());
			vo.setResult("");//ó��, ��ó�� ��� ��ȸ
			// (1) �ֹ���ȣ�� ���� �ֹ���� ��ȸ
			List<OrderVO> orderList = orderService.getListOrderById(vo);
			// (2) �ֹ��� ���� ����
			OrderVO orderDetail = new OrderVO();
			orderDetail.setIndate(orderList.get(0).getIndate());
			orderDetail.setOseq(orderList.get(0).getOseq());
			orderDetail.setMname(orderList.get(0).getMname());
			
			// (3) �ֹ��Ѿ� ���
			int amount=0;
			for (OrderVO item : orderList) {
				amount += item.getQuantity() * item.getPrice2();
			}

			// (4) ȭ�鿡 ǥ���� ���� ����
			model.addAttribute("orderList", orderList);
			model.addAttribute("orderDetail", orderDetail);
			model.addAttribute("totalPrice", amount);
			model.addAttribute("title", "�ֹ� �� ����");
			
			// (5) mypage ȭ�� ȣ��
			return "mypage/orderDetail";
		}
	}
	
	//�� �ֹ����
	@GetMapping("/order_all")
	public String orderAllView(OrderVO vo,  HttpSession session, Model model) {
		MemberVO loginUser =(MemberVO)session.getAttribute("loginUser");
		if(loginUser == null) {
			return "mypage/mypage";
	}else {
		

		//(1) �������� �ֹ� ��ȣ ��� ��ȸ
		vo.setId(loginUser.getId());
		vo.setResult("");//ó��, ��ó�� ��� ��ȸ
		List<Integer> oseqList = orderService.getSeqOrdering(vo);
		
		//(2) ���� �� �ֹ���ȣ�� ���� �ֹ���ȣ�� ��ȸ�ϰ� ������� ����
		List<OrderVO> summaryList = new ArrayList<>();//�ֹ����� ���� ���
		
		for(int oseq : oseqList) {
			//(2-1) �� �ֹ���ȣ�� �ֹ����� ��ȸ 
			OrderVO order = new OrderVO();
			
			order.setId(loginUser.getId());
			order.setOseq(oseq);
			order.setResult("");//ó��, ��ó��
			//�ֹ� ��ȣ�� �ֹ� ���
			List<OrderVO> orderList = orderService.getListOrderById(order);
			
			//(2-2)�� �ֹ� ��� ���� ����
			OrderVO summary = new OrderVO();//��� ���� ���� ����
			summary.setOseq(orderList.get(0).getOseq());//ù��° ��� �ֹ���ȣ ����
			summary.setIndate(orderList.get(0).getIndate());
			
			//��ǰ�� ������� ����
			if (orderList.size() >= 2) {
				summary.setPname(orderList.get(0).getPname() + "��" + (orderList.size()-1) + "��");
			}else {//�ֹ���ǰ�� �ϳ��� ���.
				summary.setPname(orderList.get(0).getPname());
			}
			//�� �ֹ��� �հ� �ݾ�
			int amount=0;
			for (OrderVO item : orderList) {
				amount += item.getQuantity() * item.getPrice2();
			}
			summary.setPrice2(amount);//�հ�ݾ� ����
			
			summaryList.add(summary);//�� �ֹ���� ������ ����Ʈ�� �߰�
			
		}
		
		//(3) ȭ�鿡 ������ �����͸� ����
		model.addAttribute("orderList", summaryList);
		model.addAttribute("title", "MY PAGE(�� �ֹ�����)");
		
		
	}
		return "mypage/mypage";
		
		
	}
	
}
