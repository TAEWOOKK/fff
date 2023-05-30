package com.ezen.biz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.biz.dao.AdminDAO;
import com.ezen.biz.dto.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDao;
	
	@Override	//관리자id 인증 - 관리자 계정 유무 체크
	public int adminCheck(AdminVO vo) {
		
		String pwd_in_db = adminDao.adminCheck(vo.getId());
		
		if(pwd_in_db == null) {
			//관리자 계정이 존재하지 않을 경우
			return -1;
		} else if(pwd_in_db.equals(vo.getPwd())) {
			//계정이 존재하고, 비밀번호 일치할 경우
			return 1;
		} else {
			//관리자 계정 비밀번호가 일치하지 않을 경우
			return 0;
			
		}
	}

	@Override	//관리자 정보 조회
	public AdminVO getAdmin(String id) {
		return adminDao.getAdmin(id);
	}

}
