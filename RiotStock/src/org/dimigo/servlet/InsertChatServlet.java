package org.dimigo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/insertChat")
public class InsertChatServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, IAction> actions = new HashMap<>();

	public InsertChatServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		try {
			String text = request.getParameter("text");
			System.out.println(text);
			HttpSession session = request.getSession();
			UserVO user = (UserVO) session.getAttribute("user");
			if(user == null) throw new Exception("댓글을 쓰기 위해서 로그인이 필요합니다");
			Chat chat = new Chat();
			chat.setText(text);
			chat.setUserId(user.getId());
			ChatService service = new ChatService();
			service.insertChat(chat);
			obj.addProperty("msg", "success");
		} catch (Exception e) {
			obj.addProperty("msg", "error");
			obj.addProperty("error", e.getMessage());
		} finally {
			out.write(gson.toJson(obj));
			out.close();
		}
	}
}
