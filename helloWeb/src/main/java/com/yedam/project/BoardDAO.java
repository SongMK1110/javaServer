package com.yedam.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.emp.EmpVO;


public class BoardDAO {
	String url = "jdbc:oracle:thin:@192.168.0.50:1521:xe";
	String user = "dev";
	String pass = "dev";

	Connection conn;
	Statement stmt = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String sql;

	public void connect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("에러발생.");
			e.printStackTrace();
		}
	}
	
// CRUD 처리
	// 조회
	public List<BoardVO> boardVoList() {
		connect();
		sql = "select * from board";
		List<BoardVO> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setNo(rs.getInt("no"));
				board.setTitle(rs.getString("title"));
				board.setUsername(rs.getString("username"));
				board.setWday(rs.getString("wday"));

				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	// 단건조회
	public BoardVO getBoard(int no) {
		sql = "select * from board where no = " + no;
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				BoardVO board = new BoardVO();
				board.setTitle(rs.getString("title"));
				board.setUsername(rs.getString("username"));
				board.setText(rs.getString("text"));
				return board;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 조회된 데이터 없음.
	}

	// 입력
	public int addBoard(BoardVO board) {
		connect();
		sql = "insert into board (no, title, username, wday, text) "
		+ " values(user_no.NEXTVAL, ?, ?, to_char(sysdate,'yyyy.mm.dd hh24:mi'), ?)";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql); // 기능은 똑같은데 파라미터(?) 처리할 때 좋음
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getUsername());
			psmt.setString(3, board.getText());

			r = psmt.executeUpdate(); // 처리된 건수 (성공하면 1)

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r; // 성공하면 1 실패하면 0
	}

	// 수정
	public int updateBoard(int no, String title, String text) {
		connect();
		sql = "update board set title = ?, text = ?  where no = ?";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql); // 기능은 똑같은데 파라미터(?) 처리할 때 좋음
			psmt.setString(1, title);
			psmt.setString(2, text);
			psmt.setInt(3, no);

			r = psmt.executeUpdate(); // 처리된 건수 (성공하면 1)

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	public int updateEmp(BoardVO board) {
		connect();
		sql = "update board set title = ?, text = ?  where no = ?";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql); // 기능은 똑같은데 파라미터(?) 처리할 때 좋음
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getText());
			psmt.setInt(3, board.getNo());
			
			r = psmt.executeUpdate(); // 처리된 건수 (성공하면 1)
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	// 삭제
	public int deleteBoard(int no) {
		connect();
		sql = "delete from board where no = ?";
		int r = 0;
		try {
			psmt = conn.prepareStatement(sql); // 기능은 똑같은데 파라미터(?) 처리할 때 좋음
			psmt.setInt(1, no);

			r = psmt.executeUpdate(); // 처리된 건수 (성공하면 1)

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}
