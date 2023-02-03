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

public class EmpModControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// service:int modEmp(EmpVO) -> serviceImpl: modEmp(EmpVO) -> dao:
		// updateEmp(EmpVO)
		RequestDispatcher rd = null;
		String method = req.getMethod();
		System.out.println(method);

		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (method.equals("POST")) {
			String eid = req.getParameter("eid");
			String fName = req.getParameter("first_name");
			String lName = req.getParameter("Last_name");
			String job = req.getParameter("job");
			String hire = req.getParameter("hire_date");
			String mail = req.getParameter("email");

			EmpVO emp = new EmpVO();
			emp.setEmployeeId(Integer.parseInt(eid));
			emp.setFirstName(fName);
			emp.setLastName(lName);
			emp.setJobId(job);
			emp.setHireDate(hire);
			emp.setEmail(mail);

			EmpService service = new EmpServiceImpl();
			int r = service.modEmp(emp);

			// 요청재지정
			try {
				if (r > 0) {
					resp.sendRedirect("empList.do");
//				rd = req.getRequestDispatcher("WEB-INF/result/addResult.jsp");
//				rd.forward(req, resp);
				} else {
					// resp.sendRedirect("WEB-INF/result/errorResult.jsp");
					rd = req.getRequestDispatcher("WEB-INF/result/errorResult.jsp");
					rd.forward(req, resp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
