package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.ProductCommentVO;

public interface CommentService {

	int saveComment(ProductCommentVO vo);

	List<ProductCommentVO> getListComment(int pseq);

}