package org.dimigo.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dimigo.service.StockService;
import org.dimigo.service.UserService;
import org.dimigo.vo.UserVO;
import org.json.simple.JSONArray;
import org.dimigo.util.Stock;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class StockListAction implements IAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, String[] params) throws Exception {
		response.setContentType("application/json;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    Gson gson = new Gson();
		JSONArray array = new JSONArray();
		
		try {
			String champion = params[0];
			String kind = params[1];
			int limit = Integer.parseInt(params[2]);
			// 비지니스 로직 처리를 위한 Service 호출
			StockService service = new StockService();
			List<Stock> result = service.getStockList(champion, kind, limit);
			for(Stock stock:result) {
				JsonObject obj = new JsonObject();
				obj.addProperty("price", stock.getPrice());
				obj.addProperty("share", stock.getShare());
				array.add(obj);
			}
		    
		} catch(Exception e) {
			JsonObject obj = new JsonObject();
			obj.addProperty("msg", "error");
			obj.addProperty("error", e.getMessage());
			array.add(obj);
		} finally {
			JsonObject obj = new JsonObject();
			out.write(gson.toJson(array));
			out.close();
		} 
	}

}
