/**
 * 
 */
package org.dimigo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import org.dimigo.vo.ChampionVO;
import org.dimigo.vo.UserVO;

/**
 * <pre>
 * org.dimigo.dao
 *  |_ UserDao
 * 
 * 1. 개요 :
 * 2. 작성일 : 2017. 10. 6.
 * </pre>
 *
 * @author : teacher
 * @version : 1.0
 */
public class UserDao2 {

	private static UserDao2 dao = new UserDao2();
	
	private UserDao2() {
		
	}
	
	public static UserDao2 getInstance() {
		return dao;
	}
	
	private Connection getConnection() throws Exception {		
		String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/riotstock";
		String userId = "root";
		String userPwd ="hasunoong";
	  
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(jdbcUrl, userId, userPwd);
			
		return conn;		
	}
	
	public UserVO searchUser(UserVO vo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT ID, NAME, NICKNAME, CHAMPION, STOCKINFO, MONEY FROM USER WHERE ID=? AND PWD=?");
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			rs = pstmt.executeQuery();
			
			UserVO result = null;
			if(rs.next()) {
				result = new UserVO();
				result.setId(rs.getString(1));
				result.setName(rs.getString(2));
				result.setNickname(rs.getString(3));
				result.setChampion(rs.getString(4));
				result.setStockInfo(rs.getString(5));
				result.setMoney(rs.getInt(6));
			}
			
			return result;
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("사용자 조회 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	
	public UserVO searchUserById(UserVO vo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT ID, NAME, NICKNAME, CHAMPION, STOCKINFO, MONEY FROM USER WHERE ID=?");
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			
			UserVO result = null;
			if(rs.next()) {
				result = new UserVO();
				result.setId(rs.getString(1));
				result.setName(rs.getString(2));
				result.setNickname(rs.getString(3));
				result.setChampion(rs.getString(4));
				result.setStockInfo(rs.getString(5));
				result.setMoney(rs.getDouble(6));
			}
			
			return result;
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("사용자 조회 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	public void updateUserMoney(String userId, double money) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("UPDATE USER SET MONEY = MONEY + ? WHERE ID = ?");
			pstmt.setDouble(1, money);
			pstmt.setString(2, userId);
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 0) throw new Exception("사용자 수정 실패");
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("사용자 등록 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	public void updateUserStock(String userId, int championId, double shared) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			UserVO user = new UserVO();
			user.setId(userId);
			user = searchUserById(user);
			user.setStockInfoByIdx(championId, user.getStockInfo()[championId] + shared);
			pstmt = conn.prepareStatement("UPDATE USER SET STOCKINFO = ? WHERE ID = ?");
			pstmt.setString(1, user.getStockInfoStr());
			pstmt.setString(2, userId);
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 0) throw new Exception("사용자 수정 실패");
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("사용자 등록 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	public void insertUser(UserVO vo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Random random = new Random();
			conn = getConnection();
			pstmt = conn.prepareStatement("INSERT INTO USER VALUES(?, ?, ?, ?, ?, \"0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0\", 200)");
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getNickname());
			pstmt.setString(5, ChampionVO.id2nameEN[random.nextInt(139)+1]);
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 0) throw new Exception("사용자 등록 실패");
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("사용자 등록 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
}
