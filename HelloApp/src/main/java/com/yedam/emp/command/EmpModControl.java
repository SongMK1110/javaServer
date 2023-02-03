package com.yedam.emp.command;

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
		// service:int modEmp(EmpVO) -> serviceImpl: modEmp(EmpVO) -> dao: updateEmp(EmpVO)
		RequestDispatcher rd = null;
		
		String id = req.getParameter("id");
		String lName = req.getParameter("Last_name");
		String fName = req.getParameter("first_name");
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
//				rd = req.getRequestDispatcher("WEB-INF/result/addResult.jsp");
//				rd.forward(req, resp);
			} else {
				//resp.sendRedirect("WEB-INF/result/errorResult.jsp");
				rd = req.getRequestDispatcher("WEB-INF/result/errorResult.jsp");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
