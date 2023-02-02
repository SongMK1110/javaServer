package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.emp.command.LoginControl;
import com.yedam.emp.command.ServiceControl;

@WebServlet("*.do")
public class FrontController extends HttpServlet{
	
	// url패턴과 실행할 프로그램과 매핑
	Map<String, Command> map;
	
	public FrontController() {
		map = new HashMap<>();
	}
	
	@Override
	public void init() throws ServletException {
		map.put("/service.do", new ServiceControl());
		map.put("/login.do", new LoginControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// url 패턴을 확인 => 요청페이지 어떤지 ?
		String uri = req.getRequestURI(); // /HelloApp/sample.do 
		String context = req.getContextPath(); // /HelloApp
		String page = uri.substring(context.length());
		
//		System.out.println("uri: " + uri);
//		System.out.println("context: " + context);
		System.out.println(page);
		
		Command command = map.get(page);
		command.exec(req, resp);
	}
	
	@Override
	public void destroy() {
		
	}
}
