/**
 * 
 */
package org.dimigo.service;

import java.sql.Connection;
import java.util.List;

import org.dimigo.dao.UserDao;
import org.dimigo.dao.UserDao2;
import org.dimigo.vo.UserVO;

/**
 * <pre>
 * org.dimigo.service
 *  |_ LoginService
 * 
 * 1. 개요 :
 * 2. 작성일 : 2017. 10. 5.
 * </pre>
 *
 * @author : teacher
 * @version : 1.0
 */
public class UserService extends AbstractService {

	public UserVO login(UserVO user) throws Exception {
		UserDao2 dao = UserDao2.getInstance();
		UserVO result = dao.searchUser(user);

		if (result == null)
			throw new Exception("아이디 또는 비밀번호를 다시 확인하세요.");

		return result;
	}

	public void signup(UserVO user) throws Exception {
		UserDao2 dao = UserDao2.getInstance();

		// 사용중인 아이디인지 먼저 체크
		UserVO result = dao.searchUserById(user);
		if (result != null) {
			throw new Exception("이미 사용중인 아이디입니다.");
		}

		// DB에 사용자정보 등록
		dao.insertUser(user);
	}

	public List<UserVO> searchUserList() throws Exception {

		Connection conn = null;

		try {
			conn = getConnection();
			

			UserDao dao = new UserDao(conn);

			return dao.searchUserList();

		} finally {
			if (conn != null)
				conn.close();
		}
	}
}
