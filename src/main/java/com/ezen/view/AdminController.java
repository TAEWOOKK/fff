package com.ezen.view;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.biz.dto.AdminVO;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.OrderVO;
import com.ezen.biz.dto.ProductVO;
import com.ezen.biz.dto.QnaVO;
import com.ezen.biz.dto.SalesQuantity;
import com.ezen.biz.service.AdminService;
import com.ezen.biz.service.MemberService;
import com.ezen.biz.service.OrderService;
import com.ezen.biz.service.ProductService;
import com.ezen.biz.service.QnaService;

import utils.Criteria;
import utils.PageMaker;

@Controller
@SessionAttributes("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private QnaService qnaService;
	
	@GetMapping("/admin_login_form")
	public String adminLoginView() {
		
		return "admin/main";
	}
	
	@PostMapping("/admin_login")
	public String adminLogin(AdminVO vo, Model model) {
		// (1) ������ ID ����
		int result = adminService.adminCheck(vo);
		
		// (2) ��������� �̸�
		//     -- ������ ���� ��ȸ
		//     -- ��ǰ��� ȭ������ �̵�
		if (result == 1) {
			model.addAttribute("admin", adminService.getAdmin(vo.getId()));
			
			return "redirect:admin_product_list";
		} else {
		
			// (3) ������ �������̸�
			//     -- �޽����� �����ϰ� �α���ȭ������ �̵�
			if (result == 0 || result == -1) {
				model.addAttribute("message", "���̵�, ��й�ȣ�� Ȯ���� �ּ���.");
			} 
			
			return "admin/main";
		} 
	}
	
	@GetMapping("/admin_logout")
	public String adminLogout(SessionStatus status) {
		
		status.setComplete();  // ���� ����
		
		return "admin/main"; 
	}
	
	/* ����¡ ó�� �� �ҽ�
	@RequestMapping("/admin_product_list")
	public String adminProductList(
			@RequestParam(value="key", defaultValue="") String name,
			Model model) {
		// (1) ��ü ��ǰ��� ��ȸ
		List<ProductVO> prodList = productService.getListProduct(name);
		
		// (2) ���� ��ü�� ��ǰ ��� ����
		model.addAttribute("productList", prodList);
		model.addAttribute("productListSize", prodList.size());
		
		// (3) ȭ�� ȣ��: productList.jsp
		return "admin/product/productList";
	}
	*/

	// ����¡ ó�� �� �ҽ�
	@RequestMapping("/admin_product_list")
	public String adminProductList(
			@RequestParam(value="key", defaultValue="") String name,
			Criteria criteria, Model model) {
		// (1) �������� ��ǰ��� ��ȸ
		List<ProductVO> prodList = productService.getListProductWithPaging(criteria, name);
		
		// (2) ȭ�鿡 ǥ���� ������ ��ư ���� ����
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(criteria);
		pageMaker.setTotalCount(productService.countProductList(name));  // �� �Խñ��� ���� ����
		
		// (2) ���� ��ü�� ��ǰ ��� ����
		model.addAttribute("productList", prodList);
		model.addAttribute("productListSize", prodList.size());
		model.addAttribute("pageMaker", pageMaker);
		
		// (3) ȭ�� ȣ��: productList.jsp
		return "admin/product/productList";
	}
	
	@RequestMapping("/admin_product_detail")
	public String adminProductDetail(ProductVO vo, Model model, Criteria cri) {
		String[] kindList = {"", "��", "����", "����", "������", "����Ŀ��", "����"};
		
		ProductVO product = productService.getProduct(vo);
		model.addAttribute("productVO", product);
		int index = Integer.parseInt(product.getKind());
		model.addAttribute("kind", kindList[index]);
		model.addAttribute("criteria", cri);
		
		return "admin/product/productDetail";
	}
	
	// ��ǰ ��� ȭ�� view
	@GetMapping("/admin_product_write_form")
	public String adminProductWriteView(Model model) {
		String kindList[] = {"��", "����", "����", "������", "����Ŀ��", "����"};
		
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productWrite";
	}
	
	// ��ǰ ���
	//text�� VO ��ü�� ���, �������� ������ requestParam ��ü�� ��� ��ȯ
	@PostMapping("/admin_product_write")
	public String adminProductWriteAction(ProductVO vo, HttpSession session,
				@RequestParam(value="product_image") MultipartFile uploadFile) {
		System.out.println("=====[ adminProductWriteAction() ȣ�� ���� ]=====");
		
		//���ε� ������ ���ε� �Ǿ����� Ȯ��
		if(!uploadFile.isEmpty()) {
			System.out.println(">>> if�� ���");
			
			//���ε� �Ǿ����� ���ϸ��� �����ؼ� vo ��ü�� ���� ������ �־��ش�
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);
			
			//�̹��� ������ ��ġ ���� : WEB-INF/resources/product_images ������ session ��ü�� ��Ƽ� ����
			//getServletContext(): ������Ʈ ������ ������ ����
			//getRealPath(): ������ ����� ���� ��ũ ���� ��ġ�� �˷���
			//�Ʒ��� �ڵ�� �ᱹ ������Ʈ ������ ������ �����ؼ� ������ ��η� �����ϱ� ���ؼ� image_path ��ü�� ����
			String image_path = session.getServletContext()
		               .getRealPath("WEB-INF/resources/product_images/");

			System.out.println("[image_path] = " + image_path);
			
			//���� ��η� ����
			try {
				uploadFile.transferTo(new File(image_path + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		//�Է��� ��ǰ ���� ����
		productService.insertProduct(vo);	

		System.out.println("[vo] = " + vo);
		return "redirect:admin_product_list";
	}
	
	// ��ǰ ���� ������ �� ���� ��ư - ��ǰ ���� ȭ�� ����
	@GetMapping("/admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {
		System.out.println("=====[ adminProductUpdateView() ȣ�� ���� ]=====");
		
		String kindList[] = {"��", "����", "����", "������", "����Ŀ��", "����"};
		
		// 1) ��ǰ �� ��ȸ
		ProductVO product = productService.getProduct(vo);
		
		// 2) Model ��ü�� ��ǰ ������ ����
		model.addAttribute("productVO", product);
		model.addAttribute("kindList", kindList);
		
		// 3) ȭ�� ȣ��
		return "admin/product/productUpdate";
	}
	
	//��ǰ ���� ������ �� ������ư - ��ǰ ���� ����
	//productUpdate.jsp �� nonmakeImg, product_image ������ vo�� ������� �ʾұ� ������ �Ķ���͸� ������ �Է�
	@PostMapping("/admin_product_update")
	public String adminProductUpdate(ProductVO vo, HttpSession session,
				@RequestParam(value="nonmakeImg") String org_image,
				@RequestParam(value="product_image") MultipartFile uploadFile) {
		System.out.println("=====[ adminProductUpdate() ȣ�� ���� ]=====");
		
		//���ε� ������ ���ε� �Ǿ����� Ȯ��
		if(!uploadFile.isEmpty()) { // if��: ��ǰ �̹����� ����� ���
			System.out.println(">>> if�� ���");
			
			//���ε� �Ǿ����� ���ϸ��� �����ؼ� vo ��ü�� ���� ������ �־��ش�
			String fileName = uploadFile.getOriginalFilename();
			vo.setImage(fileName);
			
			//�̹��� ������ ��ġ ���� : WEB-INF/resources/product_images ������ session ��ü�� ��Ƽ� ����
			//getServletContext(): ������Ʈ ������ ������ ����
			//getRealPath(): ������ ����� ���� ��ũ ���� ��ġ�� �˷���
			//�Ʒ��� �ڵ�� �ᱹ ������Ʈ ������ ������ �����ؼ� ������ ��η� �����ϱ� ���ؼ� image_path ��ü�� ����
			String image_path = session.getServletContext()
		               .getRealPath("WEB-INF/resources/product_images/");
				System.out.println("[image_path] = " + image_path);
			
			//���� ��η� ����
			try {
				uploadFile.transferTo(new File(image_path + fileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		} else { // else��: ���� �̹��� ���
			vo.setImage(org_image);
		}
		
		// ����Ʈ ��ǰ, �ű� ��ǰ
        System.out.println("���� ��)useyn = " + vo.getUseyn());
        System.out.println("���� ��)bestyn = " + vo.getBestyn());
        if(vo.getUseyn() == null || vo.getUseyn().equals("n")) {
           
           vo.setUseyn("n");
        }else {
        
           vo.setUseyn("y");
        }
        
        if(vo.getBestyn() == null || vo.getBestyn().equals("n")) {
           
           vo.setBestyn("n");
        }else {
        
           vo.setBestyn("y");
        }
		
		System.out.println("���� ��)useyn = " + vo.getUseyn());
		System.out.println("���� ��)bestyn = " + vo.getBestyn());
		
		productService.updateProduct(vo);
		
		return "redirect:admin_product_list";
	}
	
	// �ֹ� ��ü ��ȸ
	@RequestMapping("/admin_order_list")
	public String adminOrderList(Model model,
				@RequestParam(value="key", defaultValue="") String mname) {
		System.out.println("=====[ adminOrderList() ȣ�� ���� ]=====");
		
		List<OrderVO> orderList = orderService.getListOrder(mname);
		
		model.addAttribute("orderList", orderList);
		
		return "/admin/order/orderList";
	}
	
	//�ֹ� ���� ���� ���� - �ֹ� �Ϸ� ó��
	//�Է� �Ķ����: �ֹ��Ϸ� ó���� result �׸��� ���ֹ���ȣ(odseq) �迭 ����
	//int[] oseq = int oseq[]
	@RequestMapping("admin_order_save")
	public String adminOrderSave(OrderVO vo, HttpSession session,
			@RequestParam(value="result") int oseq[]) {
		System.out.println("=====[ adminOrderSave() ȣ�� ���� ]=====");
		
		for(int i : oseq) {
			orderService.updateOrderResult(i);
		}
		
		return "redirect:admin_order_list";
	}
	
	/*//����� ����
	@PostMapping("admin_order_save")
	public String adminOrderSave(@RequestParam(value="result") int oseq[]) {
		System.out.println("=====[ adminOrderSave() ȣ�� ���� ]=====");
		
		for(int i; i<odseq.length>0; i++) {
			orderService.updateOrderResult(odseq[i]);
		}
		
		return "redirect:admin_order_list";
	}
	*/
	
	//ȸ�� ����Ʈ ��ȸ
	@RequestMapping("/admin_member_list")
	public String listMember(MemberVO vo, Model model,
				@RequestParam(value="key", defaultValue="") String name) {
		
		List<MemberVO> memberList = memberService.listMember(name);
		
		model.addAttribute("memberList", memberList);
		
		return "/admin/member/memberList";
	}
	
	//qna ����Ʈ ��ȸ
	@GetMapping("/admin_qna_list")
	public String qnaList(Model model) {
		
		List<QnaVO> qnaList = qnaService.listAllQna();
		
		model.addAttribute("qnaList", qnaList);
		
		return "admin/qna/qnaList";
	}
	
	//qna ������
	@PostMapping("/admin_qna_detail")
	public String adminQnaDetail(Model model,
						@RequestParam(value="qseq") int qseq) {
		
		QnaVO qnaDetail = qnaService.getQna(qseq);
		
		model.addAttribute("qnaVO", qnaDetail);
		
		return "admin/qna/qnaDetail";
	}
	
	//�Խñ� �亯 ó��
	@PostMapping("/admin_qna_repsave")
	public String adminQnaDetail(@RequestParam(value="reply") String reply,
								@RequestParam(value="qseq") int qseq) {
		
		QnaVO vo = new QnaVO();
		
		vo.setQseq(qseq);
		vo.setReply(reply);
		
		qnaService.adminQnaDetail(vo);
		
		return "redirect:admin_qna_list";
	}	
	
	// ��ǰ�� �Ǹ� ���� view
	@RequestMapping("/admin_sales_record_form")
	public String adminSalesRecordView() {
		
		//��Ʈ�� ǥ���� jsp ȭ�� ǥ��
		return "admin/order/salesRecords";
	}
	
	// ��ǰ�� �Ǹ� ���� chart(�Ǹ� �Ϸ�� ��ǰ���� chart�� ��Ÿ��)
	@RequestMapping("/sales_record_chart")
	@ResponseBody
	public List<SalesQuantity> salesRecordChart() {
		
		List<SalesQuantity> listSales = orderService.getProductSales();
		
		return listSales;	
	}
	
	//���̵� ã��
	
	
}


