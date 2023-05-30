package com.ezen.biz.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductCommentVO {
	
	//상품일련번호, 상품평 내용, 작성자, 작성일, 수정일
	private int comment_seq;
	private int pseq;
	private String content;
	private String writer;
	private Date regdate;
	private Date modifydate;

}
