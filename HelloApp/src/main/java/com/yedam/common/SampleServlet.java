package com.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample")
public class SampleServlet extends HttpServlet{
	// 서블릿 생명주기: 인스턴스 -> init -> service -> destroy
	
	public SampleServlet() {
		System.out.println("생성자 호출");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init 실행: 서버 실행 후 한번만 실행됨.");
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		System.out.println("service 실행: 해당 url을 호출할 때마다 실행됨.");
	}
	
	@Override
	public void destroy() {
		System.out.println("서버가 종료될 때 한번 실행됨.");
	}
}
