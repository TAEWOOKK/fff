package com.ezen.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.service.MemberService;

@Controller
@SessionAttributes("loginUser")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	
	//���ȭ�� ǥ��
	//<li><a href="contract">JOIN</a></li> header.jsp �κ�
	@GetMapping(value="/contract")
	public String contractView() {
		
		//member ���� �ؿ� contract.jsp ��� ��
		return "member/contract";
	}

	
	
	@PostMapping("/join_form")
	public String joinView() {
		
		return "member/join";
	}
	
	
	
	/*
	 * �α��� ȭ��ǥ��
	 */
	@GetMapping("/login_form")
	public String loginView() {
	
		return "member/login";
		
	}
	
	
	
	/*
	 * �α��� ó��
	 */
	@PostMapping("/login")
	public String loginAction(MemberVO vo, Model model) {
	
		int result = memberService.loginID(vo);
		
		if(result == 1) {
			model.addAttribute("loginUser", memberService.getMember(vo.getId()));
			return "redirect:index"; // �α��� ������
		} else {
			return "member/login_fail"; // �α��� ���н�
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status ) {
		
		status.setComplete();
		return "member/login";
	}
	

	/*
	 *  ID �ߺ�üũ ȭ�� ǥ�� �� ��� ����
	 */
	@GetMapping(value="/id_check_form")
	public String idCheckView(MemberVO vo, Model model) {
		
		// id �ߺ�Ȯ�� ��ȸ  , DAO�� ���� id���� Ȯ��
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		
		return "member/idcheck";
			
	}
	
	/*
	 *  ID �ߺ�üũ ����â���� �ѹ��� Ȯ�� �Ҷ� 
	 */
	@PostMapping("/id_check_form")
	public String idCheckAString(MemberVO vo, Model model) {
		
		// id �ߺ�Ȯ�� ��ȸ  , DAO�� ���� id���� Ȯ��
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		
		return "member/idcheck";
			
	}
	
	
	
	//ȸ������ ó��
//  <form id="join" action="join" method="post" name="formm"> id �κ�
	@PostMapping("/join") 
	public String joinAction(MemberVO vo,
			@RequestParam(value = "addr1", defaultValue = "") String addr1,
			@RequestParam(value = "addr2", defaultValue = "") String addr2) {
		
		
		
		// MemberVO ���� address �ϳ��� ���� �Ǳ� ������ addr1(�ּ�) + addr2(���ּ�) �� ���ļ� ����.
		vo.setAddress(addr1 + " " + addr2); 
		
		memberService.insertMember(vo); // �Է°��� �����ͺ��̽��� ����
		
		return "member/login"; //����ó�� �� �Ѿ�� ������ login.jsp
	}
	
	
	@GetMapping("/find_zip_num")
	public String findZipNumView() {
		
		return "member/findZipNum";
	}
	
	
	@PostMapping("/find_zip_num")
	public String findZipAction(AddressVO vo, Model model) {

		List<AddressVO> addressList = memberService.selectAddressByDong(vo.getDong());
		
		model.addAttribute("addressList", addressList);
		
		return "member/findZipNum";
	
	}
	
	//���̵� ã�� â ����
	@RequestMapping("/find_id_form")
	public String findIdFormView() {
		
		return "member/findIdAndPassword";
	}
	
	//���̵� ã��
	@RequestMapping("/find_id")
	public String findIdAction(MemberVO vo, Model model) {
		
		String id = memberService.selectIdByNameEmail(vo);
		
		if(id != null) { //���̵� ����
			model.addAttribute("id", id);
			model.addAttribute("message", 1);
			
		} else {
			model.addAttribute("message", -1);
		}
		
		return "member/findResult";
	}

	//��й�ȣ ã��
	@RequestMapping("/find_pwd")
	public String findPwdAction(MemberVO vo, Model model) {
		
		String pwd = memberService.selectPwdByIdNameEmail(vo);
		
		if(pwd != null) { //�����ϴ� ID ���
			model.addAttribute("id", vo.getId());
			model.addAttribute("message", 1);
		} else {
			model.addAttribute("message", -1);
		}
		
		return "member/findPwdResult";
	}
	
	//��� ã�� �� �� ��й�ȣ ����
	@PostMapping("/change_pwd")
	public String changePwdAction(MemberVO vo, Model model) {
		
		memberService.changePwd(vo);
		
		return "member/changePwdOk";
	}
	
	
	
}
