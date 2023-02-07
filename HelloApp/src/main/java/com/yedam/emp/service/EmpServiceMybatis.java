package com.yedam.emp.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.emp.vo.EmpVO;

// EmpServiceImpl : jdbc
// EmpServiceMybatis : mybatis
public class EmpServiceMybatis implements EmpService {

	SqlSessionFactory sessionFactory = DataSource.getInstance();
	SqlSession session = sessionFactory.openSession(); // openSession(true) 자동커밋

	@Override
	public List<EmpVO> empList() {
		return session.selectList("com.yedam.emp.mapper.EmpMapper.empList");
	}

	@Override
	public int addEmp(EmpVO emp) {
		int r = session.insert("com.yedam.emp.mapper.EmpMapper.addEmp", emp);
		if (r > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		return r;
	}

	@Override
	public EmpVO getEmp(int empId) {
		return session.selectOne("com.yedam.emp.mapper.EmpMapper.getEmp", empId);
	}

	@Override
	public int modEmp(EmpVO emp) {
		int r = session.update("com.yedam.emp.mapper.EmpMapper.modEmp", emp);
		if (r > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		return r;
	}

	@Override
	public Map<String, String> jobList() {
		return null;
//		return session.selectMap("com.yedam.emp.mapper.EmpMapper.getEmp.jobList");
	}

	@Override
	public int removeEmp(int id) {
		int r = session.delete("com.yedam.emp.mapper.EmpMapper.removeEmp", id);
		if (r > 0) {
			session.commit();
		} else {
			session.rollback();
		}
		return r;
	}

}
