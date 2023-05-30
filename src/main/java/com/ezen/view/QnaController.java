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
	
	//�α��� ������ QnA ��� ��ȸ
	@GetMapping("/qna_list")
	public String qnaList(HttpSession session, Model model) {
		//�α����� �Ǿ� �ִ��� Ȯ��. 
		//session�� �α��� ������ ����ִ��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
				
		//�α����� �ȵǾ� ���� ���, �α��� �������� �̵�
		if(loginUser == null) {
			return "member/login";
		
		//�α����� �Ǿ� ���� ���,  
		} else {
			//����� ���̵� �������� qna ��� ��ȸ
			List<QnaVO> listQna = qnaService.getListQna(loginUser.getId());
			
			//ȭ�鿡 ������ qna ��� ����
			model.addAttribute("qnaList", listQna);
			
			//QnaList ȭ�� ǥ��
			return "qna/qnaList";
		}
	}
	
	@GetMapping("/qna_view")
	public String qnaView(HttpSession session, Model model, QnaVO vo) {
		//�α����� �Ǿ� �ִ��� Ȯ��. 
		//session�� �α��� ������ ����ִ��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
						
		//�α����� �ȵǾ� ���� ���, �α��� �������� �̵�
		if(loginUser == null) {
			return "member/login";
		
		//�α����� �Ǿ� ���� ���,  
		} else {
			QnaVO qnaVO = qnaService.getQna(vo.getQseq());
			
			model.addAttribute("qnaVO", qnaVO);
			
			return "qna/qnaView";
		}
	}
	
	@GetMapping("/qna_write_form")
	public String qnaWriteView(HttpSession session, Model model, QnaVO vo) {
		//�α����� �Ǿ� �ִ��� Ȯ��. 
		//session�� �α��� ������ ����ִ��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
								
		//�α����� �ȵǾ� ���� ���, �α��� �������� �̵�
		if(loginUser == null) {
			return "member/login";
		
		//�α����� �Ǿ� ���� ���,  
		} else {	
			return "qna/qnaWrite";
		}
	}
	
	@PostMapping("/qna_write")
	public String qnaWriteAction(HttpSession session, Model model, QnaVO vo) { 
		//�α����� �Ǿ� �ִ��� Ȯ��. 
		//session�� �α��� ������ ����ִ��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
								
		//�α����� �ȵǾ� ���� ���, �α��� �������� �̵�
		if(loginUser == null) {
			return "member/login";
		
		//�α����� �Ǿ� ���� ���,  
		} else {
			vo.setId(loginUser.getId());
			
			qnaService.insertQna(vo);
			
			return "redirect:qna_list";
		}
		
	}

}


