/**
 * 
 */
package org.dimigo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.dimigo.vo.ChampionVO;
import org.dimigo.vo.UserVO;
import org.dimigo.util.Chat;
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
public class ChatDao {

	private static ChatDao dao = new ChatDao();
	
	private ChatDao() {
		
	}
	
	public static ChatDao getInstance() {
		return dao;
	}
	
	private Connection getConnection() throws Exception {		
		String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/riotstock";
		String userId = "root", userPwd = "hasunoong";
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection(jdbcUrl, userId, userPwd);
		
		return conn;		
	}
	public void insertChat(Chat chat) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("INSERT INTO CHAT (userId, text) VALUES(?, ?)");
			pstmt.setString(1, chat.getUserId());
			pstmt.setString(2, chat.getText());
			int cnt = pstmt.executeUpdate();
			
			if(cnt == 0) throw new Exception("댓글 등록 실패");
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("사용자 등록 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
	public List<Chat> searchChat(Date date, int limit) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("SELECT date, userid, text FROM CHAT WHERE date > ? LIMIT ?");
			pstmt.setTimestamp(1, new Timestamp(date.getTime()));
			pstmt.setInt(2, limit);
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			
			Chat result = null;
			List<Chat> list = new ArrayList<Chat>();
			while(rs.next()) {
				result = new Chat();
				result.setDate(rs.getTimestamp(1));
				result.setUserId(rs.getString(2));
				result.setText(rs.getString(3));
				list.add(result);
			}
			return list;
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("사용자 등록 시 오류가 발생하였습니다.");
		} finally {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}
	}
}
