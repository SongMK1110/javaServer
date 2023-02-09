package com.yedam.notice.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReplyVO {
	private int replyId;
	private int noticeId;
	private String replyTitle;
	private String replySubject;
	private String replyWriter;
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date replyDate; 
}
