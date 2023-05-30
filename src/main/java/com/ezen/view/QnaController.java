package com.ezen.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.QnaVO;
import com.ezen.biz.service.QnaService;

@Controller
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	//로그인 계정의 QnA 목록 조회
	@GetMapping("/qna_list")
	public String qnaList(HttpSession session, Model model) {
		//로그인이 되어 있는지 확인. 
		//session에 로그인 정보가 들어있는지 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				
		//로그인이 안되어 있을 경우, 로그인 페이지로 이동
		if(loginUser == null) {
			return "member/login";
		
		//로그인이 되어 있을 경우,  
		} else {
			//사용자 아이디를 조건으로 qna 목록 조회
			List<QnaVO> listQna = qnaService.getListQna(loginUser.getId());
			
			//화면에 전달할 qna 목록 저장
			model.addAttribute("qnaList", listQna);
			
			//QnaList 화면 표시
			return "qna/qnaList";
		}
	}
	
	@GetMapping("/qna_view")
	public String qnaView(HttpSession session, Model model, QnaVO vo) {
		//로그인이 되어 있는지 확인. 
		//session에 로그인 정보가 들어있는지 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
						
		//로그인이 안되어 있을 경우, 로그인 페이지로 이동
		if(loginUser == null) {
			return "member/login";
		
		//로그인이 되어 있을 경우,  
		} else {
			QnaVO qnaVO = qnaService.getQna(vo.getQseq());
			
			model.addAttribute("qnaVO", qnaVO);
			
			return "qna/qnaView";
		}
	}
	
	@GetMapping("/qna_write_form")
	public String qnaWriteView(HttpSession session, Model model, QnaVO vo) {
		//로그인이 되어 있는지 확인. 
		//session에 로그인 정보가 들어있는지 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
								
		//로그인이 안되어 있을 경우, 로그인 페이지로 이동
		if(loginUser == null) {
			return "member/login";
		
		//로그인이 되어 있을 경우,  
		} else {	
			return "qna/qnaWrite";
		}
	}
	
	@PostMapping("/qna_write")
	public String qnaWriteAction(HttpSession session, Model model, QnaVO vo) { 
		//로그인이 되어 있는지 확인. 
		//session에 로그인 정보가 들어있는지 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
								
		//로그인이 안되어 있을 경우, 로그인 페이지로 이동
		if(loginUser == null) {
			return "member/login";
		
		//로그인이 되어 있을 경우,  
		} else {
			vo.setId(loginUser.getId());
			
			qnaService.insertQna(vo);
			
			return "redirect:qna_list";
		}
		
	}

}


