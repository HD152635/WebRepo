package org.dimigo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
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
import org.dimigo.vo.ChampionVO;
import org.dimigo.vo.UserVO;
import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/search")
public class SearchServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearchServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String search = URLDecoder.decode(request.getParameter("search"), "UTF-8");
			System.out.println(search);
			search = search.trim();
			List<ChampionVO> champions = new ArrayList<ChampionVO>();
			for(String champion:ChampionVO.id2nameKR) {
				if(champion.indexOf(search) > -1)
					champions.add(new ChampionVO(champion));
			}
			for(String champion:ChampionVO.id2nameEN) {
				if(champion.toLowerCase().indexOf(search.toLowerCase()) > -1)
					champions.add(new ChampionVO(champion));
			}
			request.setAttribute("champions", champions);
	    	RequestDispatcher rd = request.getRequestDispatcher("jsp3/stock.jsp");
			rd.forward(request, response);
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
