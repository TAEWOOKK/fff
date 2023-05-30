package com.ezen.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.CommentDAO;
import com.ezen.biz.dto.ProductCommentVO;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDAO commentDao;

	@Override
	public int saveComment(ProductCommentVO vo) {
		
		return commentDao.saveComment(vo);
	}

	@Override
	public List<ProductCommentVO> getListComment(int pseq) {
		
		return commentDao.listComment(pseq);
	}

}
