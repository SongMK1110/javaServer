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
//		// service:int modEmp(EmpVO) -> serviceImpl: modEmp(EmpVO) -> dao:
//		// updateEmp(EmpVO)
		// eid=112&first_name=%EC%9B%94%EC%9A%94%EC%9D%BC&Last_name=%EC%9B%94%EC%9A%94%EC%9D%BC&email=test2%40mail.com&job=AD_VP&hire_date=2023-02-06
		RequestDispatcher rd = null;

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
			} else {
				resp.sendRedirect("errorPage.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
