package com.yedam.emp.command;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.emp.vo.EmpVO;

public class EmpModFormControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		// 수정화면: WEB-INF/views/modify.jsp 호출
		
		
		String id = req.getParameter("id");

		EmpService service = new EmpServiceImpl();
		EmpVO emp = service.getEmp(Integer.parseInt(id));

		req.setAttribute("svo", emp);

		Map<String, String> jobList = service.jobList();
		req.setAttribute("jobList", jobList);
		
		try {
			req.getRequestDispatcher("WEB-INF/views/modify.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
