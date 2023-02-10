package com.yedam.member.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class Login implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 로그인 성공하면 mypage로 이동하고
		// 로그인 실패하면 다시 로그인화면으로 이동할때 아이디와 패스워드를 확인하도록
		String id = req.getParameter("mid");
		String pw = req.getParameter("mpw");

		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberPw(pw);

		MemberService service = new MemberServiceMybatis();
		MemberVO mvo = service.login(member);
		String link = "";
		if (mvo != null) {
			HttpSession session = req.getSession();

			session.setAttribute("id", mvo.getMemberId());
			session.setAttribute("name", mvo.getMemberName());
			session.setAttribute("Auth", mvo.getResponsibility());
			
			link = "myPageForm.do";
		} else {
			req.setAttribute("result", "아이디와 패스워드를 확인!!");
			link = "member/login.tiles";
		}
		return link;
	}

}
