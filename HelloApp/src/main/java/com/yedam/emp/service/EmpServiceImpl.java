package com.yedam.emp.service;

import java.util.List;

import com.yedam.emp.dao.EmpDAO;
import com.yedam.emp.vo.EmpVO;

public class EmpServiceImpl implements EmpService{
	// jdbc 활용 db처리
	EmpDAO dao = EmpDAO.getInstance();
	
	@Override
	public List<EmpVO> empList() {
		return dao.empList();
	}

	@Override
	public int addEmp(EmpVO emp) {
		return dao.insertEmp(emp);
	}

	@Override
	public EmpVO getEmp(int empId) {
		return dao.searchEmp(empId);
	}

	@Override
	public int modEmp(EmpVO emp) {
		return dao.updateEmp(emp);
	}
}
