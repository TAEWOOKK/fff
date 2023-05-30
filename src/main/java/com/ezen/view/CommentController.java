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

@RestController		// 데이터를 리턴하는 콘트롤러
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("/list")
	public Map<String, Object> commentList(ProductCommentVO vo) {
		
		//comment.jsp에 total, commentList 두개의 값을 리턴해주어야하기 때문에
		//Map 객체 생성해서 리턴해준다
		Map<String, Object> commentInfo = new HashMap<>();
				
		//상품평 목록 조회 view
		//vo의 getPseq()를 담아담아서 commentList list로 넣어주고
		List<ProductCommentVO> commentList = commentService.getListComment(vo.getPseq());
		
		//commentInfo에 두개 값 넣어주자
		commentInfo.put("total", commentList.size());
		commentInfo.put("commentList", commentList);
		
		// 두개의 값을 넣은 commentInfo를 리턴해준다
		return commentInfo;
	}
	
	@PostMapping("/save")
	public String saveComment(ProductCommentVO vo, HttpSession session) {
		// 로그인이 되어 있는지 확인
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		// 로그인이 안되어 있는 경우 로그인 페이지로 이동
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
