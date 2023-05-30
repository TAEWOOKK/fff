package com.ezen.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.biz.dto.MemberVO;
import com.ezen.biz.dto.ProductCommentVO;
import com.ezen.biz.service.CommentService;

@RestController		// �����͸� �����ϴ� ��Ʈ�ѷ�
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("/list")
	public Map<String, Object> commentList(ProductCommentVO vo) {
		
		//comment.jsp�� total, commentList �ΰ��� ���� �������־���ϱ� ������
		//Map ��ü �����ؼ� �������ش�
		Map<String, Object> commentInfo = new HashMap<>();
				
		//��ǰ�� ��� ��ȸ view
		//vo�� getPseq()�� ��ƴ�Ƽ� commentList list�� �־��ְ�
		List<ProductCommentVO> commentList = commentService.getListComment(vo.getPseq());
		
		//commentInfo�� �ΰ� �� �־�����
		commentInfo.put("total", commentList.size());
		commentInfo.put("commentList", commentList);
		
		// �ΰ��� ���� ���� commentInfo�� �������ش�
		return commentInfo;
	}
	
	@PostMapping("/save")
	public String saveComment(ProductCommentVO vo, HttpSession session) {
		// �α����� �Ǿ� �ִ��� Ȯ��
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		// �α����� �ȵǾ� �ִ� ��� �α��� �������� �̵�
		if (loginUser == null) {
			return "not_logedin";
		} else {
			vo.setWriter(loginUser.getId());
			
			if (commentService.saveComment(vo) > 0) {
				
				return "success";
			} else {
				
				return "fail";
			}
		}
	}
}
