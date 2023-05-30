package com.ezen.biz.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class QnaVO {
	private int qseq;
	private String subject;
	private String content;
	private String reply;
	private String id;
	private String rep;
	private Date indate;
	// Getters, Setters

}
