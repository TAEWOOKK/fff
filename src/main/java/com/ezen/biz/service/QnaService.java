package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.QnaVO;

public interface QnaService {
	
	List<QnaVO> getListQna(String id);
	
	QnaVO getQna(int qseq);
	
	void insertQna(QnaVO vo);
	
	List<QnaVO> listAllQna();
	
	void adminQnaDetail(QnaVO vo);

}
