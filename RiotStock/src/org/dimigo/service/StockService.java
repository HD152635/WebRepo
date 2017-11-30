/**
 * 
 */
package org.dimigo.service;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import org.dimigo.dao.PriceDao;
import org.dimigo.dao.StockDao;
import org.dimigo.dao.UserDao;
import org.dimigo.dao.UserDao2;
import org.dimigo.util.Stock;
import org.dimigo.vo.ChampionVO;
import org.dimigo.vo.UserVO;

/**
 * <pre>
 * org.dimigo.service
 *  |_ LoginService
 * 
 * 1. 개요 :
 * 2. 작성일 : 2017. 10. 5.
 * </pre>
 *
 * @author : teacher
 * @version : 1.0
 */
public class StockService extends AbstractService {

	public List<Stock> getStockList(String champion, String kind, int limit) throws Exception {
		StockDao dao = StockDao.getInstance();
		return dao.getStockList(champion, kind, limit);
	}
	private void transStock(String kind, Stock stock) throws Exception {
		StockDao dao = StockDao.getInstance();
		UserDao2 dao2 = UserDao2.getInstance();
		String champion = stock.getStockID();
		String userId = stock.getUserID();
		if(kind == "sale") {
			List<Stock> purchases = dao.getStockList(champion, "purchase", Integer.MAX_VALUE);
			for(Stock pc:purchases) {
				if(stock.getShare() > 0 && Math.abs(pc.getPrice() - stock.getPrice()) < 0.0005) {
					System.out.println("trans completed");
					double shared = Math.min(stock.getShare(), pc.getShare());
					dao.updateStock("sale", stock, stock.getShare() - shared);
					dao.updateStock("purchase", pc, pc.getShare() - shared);
					dao2.updateUserMoney(userId, stock.getPrice() * shared);
					dao2.updateUserStock(pc.getUserID(), new ChampionVO(champion).getId(), shared);
					dao.insertStock("completed", new Stock(userId, champion, stock.getPrice(), shared));
				}
			}
		} else if(kind.equals("purchase")) {
			List<Stock> sales = dao.getStockList(champion, "sale", Integer.MAX_VALUE);
			for(Stock sale:sales) {
				if(stock.getShare() > 0 && Math.abs(sale.getPrice() - stock.getPrice()) < 0.0005) {
					System.out.println("trans completed");
					double shared = Math.min(stock.getShare(), sale.getShare());
					dao.updateStock("purchase", stock, stock.getShare() - shared);
					dao.updateStock("sale", sale, sale.getShare() - shared);
					dao2.updateUserMoney(sale.getUserID(), stock.getPrice() * shared);
					dao2.updateUserStock(userId, new ChampionVO(champion).getId(), shared);
					dao.insertStock("completed", new Stock(userId, champion, stock.getPrice(), shared));
				}
			}
		}
	}
	public void insertStock(String kind, Stock stock) throws Exception {
		StockDao dao = StockDao.getInstance();
		
		dao.insertStock(kind, stock);
		transStock(kind, stock);
	}
}
