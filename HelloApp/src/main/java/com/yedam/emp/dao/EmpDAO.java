package com.yedam.emp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.emp.vo.EmpVO;

public class EmpDAO extends DAO {
	// 싱글톤 방식 생성자: prviate, 메소드:public getInstance
	private static EmpDAO instance = new EmpDAO();

	private EmpDAO() {

	}

	public static EmpDAO getInstance() {
		return instance;
	}
	
	// 수정
	public int updateEmp(EmpVO emp) {
		connect();
		sql = "update emp_temp set first_name=?, last_name=?, email=?, job_id=?, hire_date=? where employee_id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getFirstName());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getJobId());
			psmt.setString(5, emp.getHireDate());
			psmt.setInt(6, emp.getEmployeeId());

			int r = psmt.executeUpdate();
			System.out.println(r);
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return 0;
	}
	
	// 한건조회
	public EmpVO searchEmp(int empId) {
		connect();
		sql = "select * from emp_temp where employee_id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, empId);
			rs = psmt.executeQuery();
			if(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
				
				return emp;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return null;
	}

	// 한건입력
	public int insertEmp(EmpVO emp) {
		connect();
		sql = "insert into emp_temp(employee_id, last_name, email, hire_date, job_id)" + "values(?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, emp.getEmployeeId());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getHireDate());
			psmt.setString(5, emp.getJobId());

			int r = psmt.executeUpdate();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return 0;
	}

	// 목록 조회 기능
	public List<EmpVO> empList() {
		List<EmpVO> emps = new ArrayList<>();
		connect();
		sql = "select * from emp_temp order by employee_id";
		try {
			// psmt : 쿼리실행 & 실행결과를 반환
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));

				emps.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconn();
		}
		return emps;
	}

}
