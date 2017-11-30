/**
 * 
 */
package org.dimigo.action;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dimigo.service.UserService;
import org.dimigo.util.CommonUtil;
import org.dimigo.vo.UserVO;

/**
 * <pre>
 * org.dimigo.action
 *  |_ LoginAction
 * 
 * 1. 개요 :
 * 2. 작성일 : 2017. 10. 5.
 * </pre>
 *
 * @author : teacher
 * @version : 1.0
 */
public class ListAction implements IAction {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		try {
			// 비지니스 로직 처리를 위한 Service 호출
			UserService service = new UserService();
			List<UserVO> result = service.searchUserList();
			
			request.setAttribute("list", result);
	    	
	    	RequestDispatcher rd = request.getRequestDispatcher("jsp/list.jsp");
			rd.forward(request, response);
			
		} catch(Exception e) {
			request.setAttribute("msg", "error");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("jsp/list.jsp");
			rd.forward(request, response);
		}
    	
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, String[] params) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
