package com.yedam.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/BoardJson")
public class BoardJson extends HttpServlet {

//	@Override
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("utf-8");
//
//		String id = req.getParameter("id");
//		String name = req.getParameter("name");
//		String mail = req.getParameter("mail");
//		String job = req.getParameter("job");
//		String hire = req.getParameter("hire");
//
//		BoardVO vo = new BoardVO();
//		vo.setEmployeeId(Integer.parseInt(id));
//		vo.setLastName(name);
//		vo.setEmail(mail);
//		vo.setJobId(job);
//		vo.setHireDate(hire);
//
//		System.out.println(vo);
//
//		resp.getWriter().print("complete");
//	}
//
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String parm = req.getParameter("param");
		String no = req.getParameter("no");
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String wday = req.getParameter("wday");
		String text = req.getParameter("text");

		BoardVO vo = new BoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setUsername(name);
		vo.setTitle(title);
		vo.setWday(wday);
		vo.setText(text);

		BoardDAO dao = new BoardDAO();

		// param=update => DB update
		// param = x => DB insert
		if (parm.equals("update")) {
			if (dao.updateEmp(vo) > 0) {
				resp.getWriter().print("{\"retCode\": \"Success\"}");
			} else {
				resp.getWriter().print("{\"retCode\": \"Fail\"}");
			}
		} else {
			if (dao.addBoard(vo) > 0) {
				resp.getWriter().print("{\"retCode\": \"Success\"}");
			} else {
				resp.getWriter().print("{\"retCode\": \"Fail\"}");
			}
		}

	}

//	// ????????? ??????(Inversion Of Control(IOC));
//	@Override
//	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String id = req.getParameter("del_id"); // ????????????????????? del_id??? ???????????? ??????
//
//		// {id: 101, retCode: Success/Fail}
//		Map<String, Object> map = new HashMap<>();
//		map.put("id", id);
//
//		BoardDAO dao = new BoardDAO();
//		if (dao.deleteEmp(Integer.parseInt(id)) > 0) {
//			// {"retCode": "Success"}
//			// resp.getWriter().print("{\"retCode\": \"Success\"}");
//			map.put("retCode", "Success");
//		} else {
//			// {"retCode": "Fail"}
//			// resp.getWriter().print("{\"retCode\": \"Fail\"}");
//			map.put("retCode", "Fail");
//		}
//
//		// map => json
//		Gson gson = new GsonBuilder().create();
//		resp.getWriter().print(gson.toJson(map));
//	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/json;charset=utf-8");

		BoardDAO dao = new BoardDAO();
		List<BoardVO> list = dao.boardVoList();
		// [{"id": 100, "firstName": "Hong", "email": "HONG"....}, {}, {}]
		String json = "[";
		for (int i = 0; i < list.size(); i++) {
			json += "{\"no\":" + list.get(i).getNo() + ", \"title\": \"" + list.get(i).getTitle()
					+ "\", \"username\": \"" + list.get(i).getUsername() + "\", \"wday\": \""
					+ list.get(i).getWday() + "\"}";
			if (i + 1 != list.size()) {
				json += ",";
			}
		}
		json += "]";
		resp.getWriter().print(json);
	}

}
