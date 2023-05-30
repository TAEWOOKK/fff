package com.ezen.biz.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.biz.dto.AdminVO;

@Repository
public class AdminDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public String adminCheck(String id) {
		return mybatis.selectOne("AdminMapper.adminCheck", id);
	}
	
	public AdminVO getAdmin(String id) {
		return mybatis.selectOne("AdminMapper.getAdmin", id);
	}
	
}
