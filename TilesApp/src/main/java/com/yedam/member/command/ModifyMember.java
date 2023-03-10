package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;


public class ModifyMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("mid");
		String pw = req.getParameter("mpass");
		String name = req.getParameter("mname");
		String phone = req.getParameter("mphone");
		String addr = req.getParameter("maddr");
		String rs = req.getParameter("resp");

		MemberVO vo = new MemberVO();
		vo.setMemberId(id);
		vo.setMemberPw(pw);
		vo.setMemberName(name);
		vo.setMemberPhone(phone);
		vo.setMemberAddr(addr);
		vo.setResponsibility(rs);
		
		MemberService service = new MemberServiceMybatis();
		service.modifyMember(vo);
		
		
		return "myPageForm.do";
	}

}
