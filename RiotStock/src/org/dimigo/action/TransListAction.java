package org.dimigo.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dimigo.service.PriceService;
import org.dimigo.service.StockService;
import org.dimigo.service.UserService;
import org.dimigo.vo.UserVO;
import org.json.simple.JSONArray;
import org.dimigo.util.Price;
import org.dimigo.util.Stock;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TransListAction implements IAction {

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
			int limit = Integer.parseInt(params[1]);
			System.out.println(params[0] + " " + params[1]);
			// 비지니스 로직 처리를 위한 Service 호출
			PriceService service = new PriceService();
			List<Price> result = service.getPriceList(champion, limit, true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			int d = 0;
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			for(Price price:result) {
				JsonObject obj = new JsonObject();
				obj.addProperty("value", price.getPrice());
				obj.addProperty("volume", price.getVolume());
				obj.addProperty("date", sdf.format(price.getDate()));
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
