package com.ezen.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.MemberDAO;
import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDao;
	
	
	@Override
	public MemberVO getMember(String id) {
		return memberDao.getMember(id);
	}

	@Override
	public int confirmID(String id) {
		return memberDao.confirmID(id);
	}

	@Override
	public void insertMember(MemberVO vo) {
		memberDao.insertMember(vo);
	}

	@Override
	public List<AddressVO> selectAddressByDong(String dong) {
		return memberDao.selectAddressByDong(dong);
	}

	@Override
	public int loginID(MemberVO vo) {
		return memberDao.loginID(vo);
	}

	@Override
	public List<MemberVO> listMember(String name) {
		return memberDao.listMember(name);
	}

	@Override
	public String selectIdByNameEmail(MemberVO vo) {
		return memberDao.selectIdByNameEmail(vo);
	}

	@Override
	public String selectPwdByIdNameEmail(MemberVO vo) {
		return memberDao.selectPwdByIdNameEmail(vo);
	}

	@Override
	public void changePwd(MemberVO vo) {
		memberDao.changePwd(vo); 
	}
	
}
