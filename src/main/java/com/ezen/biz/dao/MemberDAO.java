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
	
	// ȸ�� id�� �������� ����� ���� ��ȸ
	public MemberVO getMember(String id) {
		return mybatis.selectOne("MemberMapper.getMember", id);
	}

	// ȸ�� ���� ���� ��ȸ
	public int confirmID(String id) {
		String pwd = mybatis.selectOne("MemberMapper.confirmID",id);
		
		if (pwd != null)
			return 1; // id ����
		else
			return -1;
	}

	/*
	 * ȸ�� �α��� ����
	 * ���ϰ� : ID�� �����ϰ� ��й�ȣ�� ������ 1
	 * 		   ��й�ȣ�� �ٸ��� 0
	 *        ID�� �������� ������ -1�� ��ȯ
	 */
	public int loginID(MemberVO vo) {// vo = ȭ�鿡�� �Է��� ����
		
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
	
	// ȸ�� ���� �۾�
	public void insertMember(MemberVO vo) {
		mybatis.insert("MemberMapper.insertMember", vo);
	}
	
	//�ּ� �۾�
	public List<AddressVO> selectAddressByDong(String dong){
		return mybatis.selectList("MemberMapper.selectAddressByDong", dong);		
	}
	
	//ȸ�� ��� ��ȸ ����Ʈ
	public List<MemberVO> listMember(String name) {
		return mybatis.selectList("MemberMapper.listMember", name);
	}
	
	// �̸��� �̸��Ϸ� ���̵� ã��
	public String selectIdByNameEmail(MemberVO vo) {
		return mybatis.selectOne("MemberMapper.selectIdByNameEmail", vo);
	}

	// ���̵�, �̸�, �̸��Ϸ� ��й�ȣ ã�� 
	public String selectPwdByIdNameEmail(MemberVO vo) {
		return mybatis.selectOne("MemberMapper.selectPwdByIdNameEmail", vo);
	}
	
	//��й�ȣ ����
	public void changePwd(MemberVO vo) {
		mybatis.update("MemberMapper.changePwd", vo);
	}
	
}
