package com.ezen.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.AdminDAO;
import com.ezen.biz.dto.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDao;
	
	@Override	//������id ���� - ������ ���� ���� üũ
	public int adminCheck(AdminVO vo) {
		
		String pwd_in_db = adminDao.adminCheck(vo.getId());
		
		if(pwd_in_db == null) {
			//������ ������ �������� ���� ���
			return -1;
		} else if(pwd_in_db.equals(vo.getPwd())) {
			//������ �����ϰ�, ��й�ȣ ��ġ�� ���
			return 1;
		} else {
			//������ ���� ��й�ȣ�� ��ġ���� ���� ���
			return 0;
			
		}
	}

	@Override	//������ ���� ��ȸ
	public AdminVO getAdmin(String id) {
		return adminDao.getAdmin(id);
	}

}
