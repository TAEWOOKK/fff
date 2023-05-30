package com.ezen.biz.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminVO {
	//아이디, 비밀번호, 이름, 전화번호
	private String id;
	private String pwd;
	private String name;
	private String phone;

}
