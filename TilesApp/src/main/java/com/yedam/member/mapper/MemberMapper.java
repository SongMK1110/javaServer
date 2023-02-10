package com.yedam.member.mapper;

import java.util.List;

import com.yedam.member.vo.MemberVO;

public interface MemberMapper {
	public MemberVO login(MemberVO member);
	public int addMember(MemberVO member); //등록
	public List<MemberVO> memberList(); //회원전체목록
	public MemberVO getMember(String id); // 회원정보조회용
	public int updateMember(MemberVO member); //회원정보수정
	public int deleteMember(String mid); //회원삭제
	
}
