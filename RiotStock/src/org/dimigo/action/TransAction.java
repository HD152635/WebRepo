package org.dimigo.action;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dimigo.service.PriceService;
import org.dimigo.service.UserService;
import org.dimigo.vo.ChampionVO;
import org.dimigo.vo.UserVO;

import com.mysql.fabric.xmlrpc.base.Array;

public class TransAction implements IAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, String[] params) throws Exception {
		try {
			ChampionVO champion = new ChampionVO(params[0]);
			PriceService service = new PriceService();
			double[] prices = service.getAllNow();
			int rank = 1;
			double price = prices[champion.getId()];
			for(double oPrice:prices) {
				if(oPrice>price)
					rank++;
			}
			System.out.printf("%s, %d, %f\n",champion,rank,price);
			double[] rate = service.getRate(champion.getNameEN());
			request.setAttribute("win", rate[0]);
			request.setAttribute("pick", rate[1]);
			request.setAttribute("rank", rank);
			request.setAttribute("price", price);
			request.setAttribute("champion", champion);
	    	RequestDispatcher rd = request.getRequestDispatcher("jsp3/transaction.jsp");
			rd.forward(request, response);
			
		} catch(Exception e) {
			request.setAttribute("msg", "error");
			request.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("jsp3/main.jsp");
			rd.forward(request, response);
		}
    	
	}
}
