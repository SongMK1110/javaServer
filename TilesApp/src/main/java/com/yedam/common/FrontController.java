package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.member.command.*;
import com.yedam.notice.command.*;

public class FrontController extends HttpServlet{
	
	private Map<String, Command> map;
	private String charset;
	
	public FrontController() {
		map = new HashMap<String, Command>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		charset = config.getInitParameter("charset");
		
		map.put("/main.do", new MainControl());
		map.put("/second.do", new SecondControl());
		//공지사항
		map.put("/noticeList.do", new NoticeList());
		map.put("/noticeListWithTables.do", new NoticeListTable());
		map.put("/noticeDetail.do", new NoticeDetail());
		map.put("/noticeForm.do", new NoticeForm()); //글등록페이지
		map.put("/noticeAdd.do", new NoticeAdd()); //글등록
		map.put("/noticeAddJson.do", new NoticeAddJson()); //datatable연습
		map.put("/noticeListJson.do", new NoticeListJson());
		map.put("/noticeListAjax.do", new NoticeListAjax());
		map.put("/removenotice.do", new RemoveNotice());
		
		//댓글
		map.put("/replyList.do", new ReplyList());//댓글목록
		map.put("/removeReply.do", new RemoveReply());//댓글삭제
		map.put("/addReply.do", new AddReply());//댓글등록
		
		//회원관련
		map.put("/loginForm.do", new LoginForm());//로그인화면
		map.put("/login.do", new Login());
		map.put("/logout.do", new LogoutControl()); //로그아웃처리
		map.put("/myPageForm.do", new MyPageControl());//마이페이지
		map.put("/modifyMember.do", new ModifyMember());//회원수정
		map.put("/imageUpload.do", new ImageUpload());
		
		// 관리자 회원관리
		map.put("/memberManageForm.do", new MemberManager());
		map.put("/memberList.do", new MemberList());
		map.put("/addMember.do", new AddMember());
		map.put("/removeMember.do", new RemoveMember());
		map.put("/modiMember.do", new ModiMember());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
		
		String uri = req.getRequestURI();
		String context = req.getContextPath();
		String page = uri.substring(context.length());
		System.out.println(page);
		
		Command command = map.get(page);
		String viewPage = command.exec(req, resp);
		// main/main.tiles
		// notice/noticeList.tiles
		
		if(viewPage.endsWith(".tiles")) {
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req, resp);			
		} else if(viewPage.endsWith(".do")) {
			resp.sendRedirect(viewPage);
		} else if(viewPage.endsWith(".json")) {
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().print(viewPage.substring(0, viewPage.length() - 5));
		}
		
		
	}
}
