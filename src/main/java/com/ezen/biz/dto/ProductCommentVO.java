package com.ezen.biz.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductCommentVO {
	
	//��ǰ�Ϸù�ȣ, ��ǰ�� ����, �ۼ���, �ۼ���, ������
	private int comment_seq;
	private int pseq;
	private String content;
	private String writer;
	private Date regdate;
	private Date modifydate;

}
