package com.yedam.emp.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.emp.vo.EmpVO;

public class EmpControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		RequestDispatcher rd = null;
		
		String method = req.getMethod();
		System.out.println(method);
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (method.equals("GET")) {
			try {
//				resp.sendRedirect("https://www.naver.com");
				resp.sendRedirect("result/empList.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (method.equals("POST")) {
			// 입력
			String eid = req.getParameter("eid");
			String lName = req.getParameter("Last_name");
			String job = req.getParameter("job");
			String hire = req.getParameter("hire_date");
			String mail = req.getParameter("email");

			EmpVO emp = new EmpVO();
			emp.setEmployeeId(Integer.parseInt(eid));
			emp.setLastName(lName);
			emp.setJobId(job);
			emp.setHireDate(hire);
			emp.setEmail(mail);

			EmpService service = new EmpServiceImpl();
			int r = service.addEmp(emp);

			// 요청재지정
			try {
				if (r > 0) {
					resp.sendRedirect("empList.do");
//					rd = req.getRequestDispatcher("WEB-INF/result/addResult.jsp");
//					rd.forward(req, resp);
				} else {
					//resp.sendRedirect("WEB-INF/result/errorResult.jsp");
					rd = req.getRequestDispatcher("WEB-INF/result/errorResult.jsp");
					rd.forward(req, resp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		} else {
			System.out.println("알 수 없는 요청입니다.");
			
		}
	}

}
