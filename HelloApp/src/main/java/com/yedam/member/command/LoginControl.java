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

public class LoginControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// 로그인이 되면 session객체 setAttribute('id')
		
		
		String method = req.getMethod();
		System.out.println("요청방식: " + method);
		String id = req.getParameter("mid"); // form>input:name속성
		String pw = req.getParameter("mpw");
		
		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberPw(pw);
		
		MemberService service = new MemberServiceMybatis();
		MemberVO rvo = service.login(member);
		if(rvo != null) {
			try {
				resp.sendRedirect("empList.do");
				HttpSession session = req.getSession();
				
				session.setAttribute("id", rvo.getMemberId());
				session.setAttribute("name", rvo.getMemberName());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				req.setAttribute("result", "회원정보를 확인하세요");
				req.getRequestDispatcher("WEB-INF/member/login.jsp").forward(req, resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
