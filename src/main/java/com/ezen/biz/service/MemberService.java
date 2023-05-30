package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	// 회원 id를 조건으로 사용자 정보 조회
	MemberVO getMember(String id);

	// 회원 존재 여부 조회
	int confirmID(String id);
	
	// 회원 로그인 인증
	int loginID(MemberVO vo);

	void insertMember(MemberVO vo);

	//주소 작업
	List<AddressVO> selectAddressByDong(String dong);
	
	///////////// 아래는 관리자 영역 /////////////
	
	//관리자 페이지 - 회원 리스트
	List<MemberVO> listMember(String name);
	
	// 이름과 이메일로 아이디 찾기
	String selectIdByNameEmail(MemberVO vo);

	// 아이디, 이름, 이메일로 비밀번호 찾기 
	String selectPwdByIdNameEmail(MemberVO vo);
	
	// 비밀번호 변경
	void changePwd(MemberVO vo);
}