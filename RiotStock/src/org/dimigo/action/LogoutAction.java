/**
 * 
 */
package org.dimigo.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class LogoutAction implements IAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 세션 삭제
		HttpSession session = request.getSession();
    	//session.removeAttribute("user");
    	session.invalidate();
    	
    	RequestDispatcher rd = request.getRequestDispatcher("jsp3/main.jsp");
		rd.forward(request, response);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, String[] params) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
