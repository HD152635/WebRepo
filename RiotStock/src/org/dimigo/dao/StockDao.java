/**
 * 
 */
package org.dimigo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.dimigo.vo.ChampionVO;
import org.dimigo.vo.UserVO;
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
public class StockDao {

	private static StockDao dao = new StockDao();
	
	private StockDao() {
		
	}
	
	public static StockDao getInstance() {
		return dao;
	}
	
	private Connection getConnection() throws Exception {		
		String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/riotstock";
		String userId = "root", userPwd = "hasunoong";
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(jdbcUrl, userId, userPwd);
		
		return conn;		
	}
	public void updateStock(String kind, Stock stock, double share) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			if(share <= 0.0005) {
				pstmt = conn.prepareStatement("DELETE FROM stock_" + kind + "_" + stock.getStockID() + " WHERE "
						+ "(userId = ?) AND (PRICE = ?) LIMIT 1");
				pstmt.setString(1, stock.getUserID());
				pstmt.setDouble(2, stock.getPrice());
				System.out.println(pstmt);
				int cnt = pstmt.executeUpdate();
				
				if(cnt == 0) throw new Exception("주식 수정 실패");
			} else {
				pstmt = conn.prepareStatement("UPDATE stock_" + kind + "_" + stock.getStockID() + " SET SHARE = ? WHERE "
							+ "(userId = ?) AND (PRICE = ?) LIMIT 1");
				pstmt.setDouble(1, share);
				pstmt.setString(2, stock.getUserID());
				pstmt.setDouble(3, stock.getPrice());
				System.out.println(pstmt);
				int cnt = pstmt.executeUpdate();
				
				if(cnt == 0) throw new Exception("주식 수정 실패");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("주식 거래 등록 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	public void insertStock(String kind, Stock stock) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			if(kind.equals("completed")) {
				PriceDao dao = PriceDao.getInstance();
				dao.updatePriceVolume(stock.getStockID());
			}
			conn = getConnection();
			pstmt = conn.prepareStatement("INSERT INTO stock_"
					+ kind + "_" + stock.getStockID() + "(userId, price, share) VALUES(?, ?, ?)");
			pstmt.setString(1, stock.getUserID());
			pstmt.setDouble(2, stock.getPrice());
			pstmt.setDouble(3, stock.getShare());
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 0) throw new Exception("사용자 등록 실패");
			
		} catch(Exception e) {
			System.out.println(kind+ " " + stock);
			e.printStackTrace();
			throw new Exception("주식 거래 등록 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	public List<Stock> searchStockByDate(String champion, String kind, Date date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT userId, price, share FROM stock_"
			+ kind + "_" + champion + " WHERE DATE=?");
			pstmt.setDate(1, new java.sql.Date(date.getTime()));
			rs = pstmt.executeQuery();
			
			Stock result = null;
			List<Stock> list = new ArrayList<Stock>();
			System.out.println(pstmt);
			while(rs.next()) {
				result = new Stock();
				result.setUserID(rs.getString(1));
				result.setPrice(rs.getDouble(2));
				result.setShare(rs.getDouble(3));
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
	public List<Stock> getStockList(String champion, String kind, int limit) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT userId, price, share FROM stock_"
			+ kind + "_" + champion + " ORDER BY id DESC LIMIT ?");
			pstmt.setInt(1, limit);
			rs = pstmt.executeQuery();
			
			Stock result = null;
			List<Stock> list = new ArrayList<Stock>();
			System.out.println(pstmt);
			while(rs.next()) {
				result = new Stock();
				result.setStockID(champion);
				result.setUserID(rs.getString(1));
				result.setPrice(rs.getDouble(2));
				result.setShare(rs.getDouble(3));
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
