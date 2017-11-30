/**
 * 
 */
package org.dimigo.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.dimigo.dao.ChatDao;
import org.dimigo.dao.StockDao;
import org.dimigo.dao.UserDao;
import org.dimigo.util.Chat;
import org.dimigo.util.Stock;
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
public class ChatService extends AbstractService {

	public void insertChat(Chat chat) throws Exception {
		ChatDao dao = ChatDao.getInstance();

		dao.insertChat(chat);
	}
	public List<Chat> searchChat(Date date, int limit) throws Exception {
		ChatDao dao = ChatDao.getInstance();
		
		return dao.searchChat(date, limit);
	}
}
