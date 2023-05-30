package com.ezen.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

@Repository
public class MemberDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	// 회원 id를 조건으로 사용자 정보 조회
	public MemberVO getMember(String id) {
		return mybatis.selectOne("MemberMapper.getMember", id);
	}

	// 회원 존재 여부 조회
	public int confirmID(String id) {
		String pwd = mybatis.selectOne("MemberMapper.confirmID",id);
		
		if (pwd != null)
			return 1; // id 존재
		else
			return -1;
	}

	/*
	 * 회원 로그인 인증
	 * 리턴값 : ID가 존재하고 비밀번호가 같으면 1
	 * 		   비밀번호가 다르면 0
	 *        ID가 존재하지 않으면 -1을 반환
	 */
	public int loginID(MemberVO vo) {// vo = 화면에서 입력한 정보
		
		int result = -1;
		String pwd = mybatis.selectOne("MemberMapper.confirmID", vo);
		
		if(pwd == null) {
			result = -1;
		} else if(pwd.equals(vo.getPwd())) {
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}
	
	// 회원 가입 작업
	public void insertMember(MemberVO vo) {
		mybatis.insert("MemberMapper.insertMember", vo);
	}
	
	//주소 작업
	public List<AddressVO> selectAddressByDong(String dong){
		return mybatis.selectList("MemberMapper.selectAddressByDong", dong);		
	}
	
	//회원 목록 조회 리스트
	public List<MemberVO> listMember(String name) {
		return mybatis.selectList("MemberMapper.listMember", name);
	}
	
	// 이름과 이메일로 아이디 찾기
	public String selectIdByNameEmail(MemberVO vo) {
		return mybatis.selectOne("MemberMapper.selectIdByNameEmail", vo);
	}

	// 아이디, 이름, 이메일로 비밀번호 찾기 
	public String selectPwdByIdNameEmail(MemberVO vo) {
		return mybatis.selectOne("MemberMapper.selectPwdByIdNameEmail", vo);
	}
	
	//비밀번호 변경
	public void changePwd(MemberVO vo) {
		mybatis.update("MemberMapper.changePwd", vo);
	}
	
}
