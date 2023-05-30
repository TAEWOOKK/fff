package com.ezen.biz.service;

import java.util.List;

import com.ezen.biz.dto.AddressVO;
import com.ezen.biz.dto.MemberVO;

public interface MemberService {

	// ȸ�� id�� �������� ����� ���� ��ȸ
	MemberVO getMember(String id);

	// ȸ�� ���� ���� ��ȸ
	int confirmID(String id);
	
	// ȸ�� �α��� ����
	int loginID(MemberVO vo);

	void insertMember(MemberVO vo);

	//�ּ� �۾�
	List<AddressVO> selectAddressByDong(String dong);
	
	///////////// �Ʒ��� ������ ���� /////////////
	
	//������ ������ - ȸ�� ����Ʈ
	List<MemberVO> listMember(String name);
	
	// �̸��� �̸��Ϸ� ���̵� ã��
	String selectIdByNameEmail(MemberVO vo);

	// ���̵�, �̸�, �̸��Ϸ� ��й�ȣ ã�� 
	String selectPwdByIdNameEmail(MemberVO vo);
	
	// ��й�ȣ ����
	void changePwd(MemberVO vo);
}