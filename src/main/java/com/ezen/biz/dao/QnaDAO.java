package com.ezen.biz.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.QnaVO;

@Repository
public class QnaDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<QnaVO> getListQna(String id) {
		return mybatis.selectList("QnaMapper.listQna", id);
	}
	
	public QnaVO getQna(int qseq) {
		return mybatis.selectOne("QnaMapper.getQna", qseq);
	}
	
	public void insertQna(QnaVO vo) {
		mybatis.insert("QnaMapper.insertQna", vo);
	}
	
	//관리자 페이지 게시판 조회 목록
	public List<QnaVO> listAllQna() {
		return mybatis.selectList("QnaMapper.listAllQna");
	}
	
	//관리자 페이지 게시판 상세보기
	public void adminQnaDetail(QnaVO vo) {
		mybatis.update("QnaMapper.updateQna", vo);
	}
	
	

}
