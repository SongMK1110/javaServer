package com.yedam.emp.command;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceMybatis;
import com.yedam.emp.vo.EmpVO;

public class EmpList implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		EmpService service = new EmpServiceMybatis();
		List<EmpVO> list = service.empList();
//		for(EmpVO emp : list) {
//			System.out.println(emp);
//		}
		
		req.setAttribute("empList", list);
		
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/result/empList.jsp");
		try {
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
