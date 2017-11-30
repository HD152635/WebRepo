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
import org.dimigo.service.StockService;
import org.dimigo.util.Stock;
import org.dimigo.vo.UserVO;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/stock/sale")
public class StockSaleServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, IAction> actions = new HashMap<>();

	public StockSaleServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    Gson gson = new Gson();
		JsonObject obj = new JsonObject();
		try {
			Double price = Double.parseDouble(request.getParameter("price"));
			price = Double.parseDouble(String.format("%.3f", price));
			String share = request.getParameter("share");
			String champion = request.getParameter("champion");
			System.out.println(price + " " + share);
			HttpSession session = request.getSession();
			UserVO user = (UserVO) session.getAttribute("user");
			if(user == null) throw new Exception("주식 거래를 위해서 로그인이 필요합니다");
			Stock stock = new Stock(user.getId(),champion,price,Double.parseDouble(share));
			StockService service = new StockService();
			service.insertStock("sale", stock);
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
