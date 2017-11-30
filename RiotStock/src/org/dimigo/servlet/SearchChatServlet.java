package org.dimigo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dimigo.action.IAction;
import org.dimigo.action.StockListAction;
import org.dimigo.action.TransAction;
import org.dimigo.dao.StockDao;
import org.dimigo.service.ChatService;
import org.dimigo.util.Chat;
import org.dimigo.util.Stock;
import org.dimigo.vo.UserVO;
import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/searchChat")
public class SearchChatServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchChatServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    Gson gson = new Gson();
		JSONArray array = new JSONArray();
		
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getParameter("date"));
			int limit = Integer.parseInt(request.getParameter("limit"));
			System.out.println(date + " " + limit);
			HttpSession session = request.getSession();
			ChatService service = new ChatService();
			List<Chat> result = service.searchChat(date, limit);
			for(Chat chat:result) {
				JsonObject obj = new JsonObject();
				obj.addProperty("userId", chat.getUserId());
				obj.addProperty("date", chat.getDate().toString());
				obj.addProperty("text", chat.getText());
				array.add(obj);
			}
		} catch (Exception e) {
			JsonObject obj = new JsonObject();
			obj.addProperty("msg", "error");
			obj.addProperty("error", e.getMessage());
			array.add(obj);
			e.printStackTrace();
		} finally {
			out.write(gson.toJson(array));
			out.close();
		}
	}
}
