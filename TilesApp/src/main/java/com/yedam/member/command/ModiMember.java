package com.yedam.member.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Command;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceMybatis;
import com.yedam.member.vo.MemberVO;

public class ModiMember implements Command {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("mid");
		String pw = req.getParameter("mpass");
		String name = req.getParameter("mname");
		String phone = req.getParameter("mphone");
		String addr = req.getParameter("maddr");
		String rs = req.getParameter("resp");

		MemberVO vo = new MemberVO();
		vo.setMemberId(id);
		vo.setMemberPw(pw);
		vo.setMemberName(name);
		vo.setMemberPhone(phone);
		vo.setMemberAddr(addr);
		vo.setResponsibility(rs);

		MemberService service = new MemberServiceMybatis();

		// 결과값을 map타입에 저장
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("member", vo);
		Gson gson = new GsonBuilder().create();

		if (service.modifyMember(vo) > 0) {
			resultMap.put("retCode", "Success");
			// return "{\"retCode\": \"Success\"}.json";
		} else {
			resultMap.put("retCode", "Fail");
			// return "{\"retCode\": \"Fail\"}.json";
		}

		return gson.toJson(resultMap) + ".json";
	}

}
