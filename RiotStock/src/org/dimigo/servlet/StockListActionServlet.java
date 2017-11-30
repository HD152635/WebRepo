package org.dimigo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dimigo.action.IAction;
import org.dimigo.action.StockListAction;

@WebServlet("/stocklist")
public class StockListActionServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, IAction> actions = new HashMap<>();

	public StockListActionServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		actions.put("stocklist", new StockListAction());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String uri = request.getRequestURI();
			String actionName = uri.substring(uri.lastIndexOf("/") + 1);

			String champion = request.getParameter("champion");
			String kind = request.getParameter("kind");
			String limit = request.getParameter("limit");
			String[] params = {champion, kind, limit};
			
			IAction action = actions.get(actionName);
			
			// Action의 execute() 실행
			action.execute(request, response, params);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<html><head><title>Error</title></head><body><h3>");
				// e.printStackTrace(out);
				out.println(e.getMessage());
				out.println("</h3></body></html>");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
