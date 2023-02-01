package com.yedam.project;

public class BoardVO {
	private int no;
	private String title;
	private String username;
	private String wday;
	private String text;
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getWday() {
		return wday;
	}
	public void setWday(String wday) {
		this.wday = wday;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return no + "        " + title + "            " + username + "           " + wday;
	}
	public String toStringSearch(int no) {
		return " 글 번호 : " + no +"\n 제목 : " + title + "\n 작성자 : " + username + "\n 내용 : " + text;
	}
}
