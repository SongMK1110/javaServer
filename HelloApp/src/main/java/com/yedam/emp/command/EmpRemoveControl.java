package com.yedam.emp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.emp.service.EmpServiceMybatis;
import com.yedam.emp.vo.EmpVO;

public class EmpRemoveControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {

		String id = req.getParameter("id");

		EmpService service = new EmpServiceMybatis();//new EmpServiceImpl();
		int r = service.removeEmp(Integer.parseInt(id));
		
		try {
			if (r > 0) {
				resp.sendRedirect("empList.do");
			} else {
				resp.sendRedirect("errorPage.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
