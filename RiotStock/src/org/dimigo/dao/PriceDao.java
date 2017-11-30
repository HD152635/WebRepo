/**
 * 
 */
package org.dimigo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.dimigo.vo.ChampionVO;
import org.dimigo.vo.UserVO;
import org.dimigo.util.Price;
import org.dimigo.util.Stock;
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
public class PriceDao {

	private static PriceDao dao = new PriceDao();
	
	private PriceDao() {
		
	}
	
	public static PriceDao getInstance() {
		return dao;
	}
	
	private Connection getConnection() throws Exception {		
		String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/riotstock";
		String userId = "root", userPwd = "hasunoong";
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(jdbcUrl, userId, userPwd);
		
		return conn;		
	}
	public List<Price> getPriceListReverse(String champion, int limit) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT price, date, volume FROM price_"
			+ champion + " ORDER BY date ASC LIMIT ?");
			pstmt.setInt(1, limit);
			rs = pstmt.executeQuery();
			
			Price result = null;
			List<Price> list = new ArrayList<Price>();
			while(rs.next()) {
				result = new Price();
				result.setPrice(rs.getDouble(1));
				result.setDate(rs.getDate(2));
				result.setVolume(rs.getInt(3));
				list.add(result);
			}
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("주식 정보 조회 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	public Price getOnePrice(String champion) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT price, date, volume, win, pick FROM price_"
			+ champion + " ORDER BY date DESC LIMIT 1");
			rs = pstmt.executeQuery();
			
			Price result = new Price();
			if(rs.next()) {
				result.setPrice(rs.getDouble(1));
				result.setDate(rs.getDate(2));
				result.setVolume(rs.getInt(3));
				result.setWin(rs.getDouble(4));
				result.setPick(rs.getDouble(5));
			}
			return result;
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("주식 정보 조회 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	public void updatePriceVolume(String champion) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("UPDATE price_"
					+ champion +  " SET VOLUME = IFNULL(VOLUME, 0) + 1 ORDER BY DATE DESC LIMIT 1");

			int cnt = pstmt.executeUpdate();
			
			if(cnt == 0) throw new Exception("가격 등록 실패");
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("사용자 등록 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	public void insertPrice(String champion, double price, double win, double pick) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Random random = new Random();
			conn = getConnection();
			pstmt = conn.prepareStatement("INSERT INTO price_"
					+ champion +  " (price, win, pick) VALUES(?, ?, ?)");
			pstmt.setDouble(1, price);
			pstmt.setDouble(2, win);
			pstmt.setDouble(3, pick);

			int cnt = pstmt.executeUpdate();
			
			if(cnt == 0) throw new Exception("가격 등록 실패");
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("사용자 등록 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	public List<Price> getPriceList(String champion, int limit) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT price, date, volume FROM price_"
			+ champion + " ORDER BY date DESC LIMIT ?");
			pstmt.setInt(1, limit);
			rs = pstmt.executeQuery();
			
			Price result = null;
			List<Price> list = new ArrayList<Price>();
			while(rs.next()) {
				result = new Price();
				result.setPrice(rs.getDouble(1));
				result.setDate(rs.getDate(2));
				result.setVolume(rs.getInt(3));
				list.add(result);
			}
			return list;
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("주식 정보 조회 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
}
