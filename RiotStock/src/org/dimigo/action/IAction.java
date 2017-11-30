/**
 * 
 */
package org.dimigo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * org.dimigo.action
 *  |_ IAction
 * 
 * 1. 개요 :
 * 2. 작성일 : 2017. 10. 5.
 * </pre>
 *
 * @author : teacher
 * @version : 1.0
 */
public interface IAction {
	
	void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

	void execute(HttpServletRequest request, HttpServletResponse response, String[] params) throws Exception;

}
