package com.yedam.emp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.emp.vo.EmpVO;

public class EmpDetailControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// 상세조회: service -> serviceImpl -> dao
		String id = req.getParameter("eid");
		
		EmpService service = new EmpServiceImpl();
		EmpVO emp = service.getEmp(Integer.parseInt(id));
		
		req.setAttribute("searchVO", emp);
		req.setAttribute("myAge", 100);
		req.setAttribute("loginId", "user1");
		
		try {
			req.getRequestDispatcher("WEB-INF/result/empDetail.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
