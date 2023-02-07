package com.yedam.member.command;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class SignOnControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// form:multipart/form-data => 처리(MultipartRequest)
		// 생성자매개값: 1)요청정보,2)저장경로,3)최대파일사이즈지정,4)인코딩,5)리네임정책 
		String savePath = req.getServletContext().getRealPath("/images"); //getServletContext() 프로젝트 제일 상위
		int maxSize = (1024 * 1024 * 10);
		String encoding = "utf-8";
		
		try {
			// 파일업로드 서블릿
			MultipartRequest multi = 
					new MultipartRequest(req, savePath, maxSize, encoding, new DefaultFileRenamePolicy());
			String id = multi.getParameter("member_id");
			String pw = multi.getParameter("member_pw");
			String nm = multi.getParameter("member_name");
			String ph = multi.getParameter("member_phone");
			String fileName = "";
			
			Enumeration<?> files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String file = (String) files.nextElement();
				System.out.println(file);
				fileName = multi.getFilesystemName(file); // 바뀐 파일 이름 읽을 때
			}
			
			MemberVO member = new MemberVO();
			member.setMemberId(id);
			member.setMemberPw(pw);
			member.setMemberName(nm);
			member.setMemberPhone(ph);
			member.setImage(fileName);
			
			MemberService service = new MemberServiceMybatis();
			if(service.addMember(member) > 0) {
				resp.sendRedirect("login.do");
			} else {				
				resp.sendRedirect("errorPage.do");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
