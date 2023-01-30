package com.yedam.emp;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empListJson")
public class EmpListJson extends HttpServlet {

	// 제어의 역전(Inversion Of Control(IOC));
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("del_id"); // 요청페이지에서 del_id로 파라미터 지정

		EmpDAO dao = new EmpDAO();
		if (dao.deleteEmp(Integer.parseInt(id)) > 0) {
			// {"retCode": "Success"}
			resp.getWriter().print("{\"retCode\": \"Success\"}");
		} else {
			// {"retCode": "Fail"}
			resp.getWriter().print("{\"retCode\": \"Fail\"}");
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");

		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empVoList();
		// [{"id": 100, "firstName": "Hong", "email": "HONG"....}, {}, {}]
		String json = "[";
		for (int i = 0; i < list.size(); i++) {
			json += "{\"id\":" + list.get(i).getEmployeeId() + ", \"firstName\": \"" + list.get(i).getFirstName()
					+ "\", \"email\": \"" + list.get(i).getEmail() + "\", \"hireDate\": \"" + list.get(i).getHireDate()
					+ "\", \"job\": \"" + list.get(i).getJobId() + "\"}";
			if (i + 1 != list.size()) {
				json += ",";
			}
		}
		json += "]";

		resp.getWriter().print(json);
	}
}
