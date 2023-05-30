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
	
	
	//약관화면 표시
	//<li><a href="contract">JOIN</a></li> header.jsp 부분
	@GetMapping(value="/contract")
	public String contractView() {
		
		//member 파일 밑에 contract.jsp 라는 뜻
		return "member/contract";
	}

	
	
	@PostMapping("/join_form")
	public String joinView() {
		
		return "member/join";
	}
	
	
	
	/*
	 * 로그인 화면표시
	 */
	@GetMapping("/login_form")
	public String loginView() {
	
		return "member/login";
		
	}
	
	
	
	/*
	 * 로그인 처리
	 */
	@PostMapping("/login")
	public String loginAction(MemberVO vo, Model model) {
	
		int result = memberService.loginID(vo);
		
		if(result == 1) {
			model.addAttribute("loginUser", memberService.getMember(vo.getId()));
			return "redirect:index"; // 로그인 성공시
		} else {
			return "member/login_fail"; // 로그인 실패시
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status ) {
		
		status.setComplete();
		return "member/login";
	}
	

	/*
	 *  ID 중복체크 화면 표시 및 결과 전송
	 */
	@GetMapping(value="/id_check_form")
	public String idCheckView(MemberVO vo, Model model) {
		
		// id 중복확인 조회  , DAO로 가서 id값을 확인
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		
		return "member/idcheck";
			
	}
	
	/*
	 *  ID 중복체크 작은창에서 한번더 확인 할때 
	 */
	@PostMapping("/id_check_form")
	public String idCheckAString(MemberVO vo, Model model) {
		
		// id 중복확인 조회  , DAO로 가서 id값을 확인
		int result = memberService.confirmID(vo.getId());
		
		model.addAttribute("message", result);
		model.addAttribute("id", vo.getId());
		
		return "member/idcheck";
			
	}
	
	
	
	//회원가입 처리
//  <form id="join" action="join" method="post" name="formm"> id 부분
	@PostMapping("/join") 
	public String joinAction(MemberVO vo,
			@RequestParam(value = "addr1", defaultValue = "") String addr1,
			@RequestParam(value = "addr2", defaultValue = "") String addr2) {
		
		
		
		// MemberVO 에는 address 하나로 저장 되기 때문에 addr1(주소) + addr2(상세주소) 를 합쳐서 저장.
		vo.setAddress(addr1 + " " + addr2); 
		
		memberService.insertMember(vo); // 입력값을 데이터베이스에 저장
		
		return "member/login"; //내용처리 후 넘어가는 페이지 login.jsp
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
	
	//아이디 찾기 창 띄우기
	@RequestMapping("/find_id_form")
	public String findIdFormView() {
		
		return "member/findIdAndPassword";
	}
	
	//아이디 찾기
	@RequestMapping("/find_id")
	public String findIdAction(MemberVO vo, Model model) {
		
		String id = memberService.selectIdByNameEmail(vo);
		
		if(id != null) { //아이디 존재
			model.addAttribute("id", id);
			model.addAttribute("message", 1);
			
		} else {
			model.addAttribute("message", -1);
		}
		
		return "member/findResult";
	}

	//비밀번호 찾기
	@RequestMapping("/find_pwd")
	public String findPwdAction(MemberVO vo, Model model) {
		
		String pwd = memberService.selectPwdByIdNameEmail(vo);
		
		if(pwd != null) { //존재하는 ID 경우
			model.addAttribute("id", vo.getId());
			model.addAttribute("message", 1);
		} else {
			model.addAttribute("message", -1);
		}
		
		return "member/findPwdResult";
	}
	
	//비번 찾은 후 새 비밀번호 변경
	@PostMapping("/change_pwd")
	public String changePwdAction(MemberVO vo, Model model) {
		
		memberService.changePwd(vo);
		
		return "member/changePwdOk";
	}
	
	
	
}
