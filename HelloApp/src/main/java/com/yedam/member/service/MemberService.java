package com.yedam.member.service;

import java.util.List;

import com.yedam.member.vo.MemberVO;

public interface MemberService {
	public MemberVO login(MemberVO member);
	public int addMember(MemberVO member); //등록
	public List<MemberVO> memberList(); //회원전체목록
	public MemberVO getMember(String id); // 회원정보조회용
}
