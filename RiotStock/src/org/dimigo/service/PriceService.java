package org.dimigo.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.dimigo.dao.PriceDao;
import org.dimigo.util.Price;
import org.dimigo.vo.ChampionVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class PriceService extends AbstractService {

	public List<Price> getPriceList(String champion, int limit, boolean isReversed) throws Exception {
		PriceDao dao = PriceDao.getInstance();

		return isReversed ? dao.getPriceListReverse(champion, limit) : dao.getPriceList(champion, limit);
	}
	public double[] getRate(String champion) throws Exception {
		PriceDao dao = PriceDao.getInstance();
		Price price = dao.getOnePrice(champion);
		if(price.getDate().getDay() != new Date().getDay() || price.getWin() == 0.0){
			String[] lines = { "top", "mid", "adc", "jungle", "support" };
			double total_win = 0.0, total_pick = 0.0;
			for (String line : lines) {
				Document doc = Jsoup.connect("http://www.op.gg/champion/" + champion + "/statistics/" + line).get();
	
				Elements rate = doc.select("div.champion-stats-trend-rate");
				String win = rate.first().text();
				String pick = rate.last().text();
				Double winr = Double.parseDouble(win.substring(0, win.length() - 1));
				Double pickr = Double.parseDouble(pick.substring(0, pick.length() - 1));
				total_win += winr * pickr;
				total_pick += pickr;
			}
			total_win /= total_pick;
			double[] ret = {total_win, total_pick};
			return ret;
		}
		double[] ret = {price.getWin(), price.getPick()};
		return ret;
	}
	private void insertPrice(String champion) throws Exception {
		double[] rate = getRate(champion);
		double win = rate[0], pick = rate[1];
		double price = Math.pow((win - 35.0), 1.5) + Math.pow(pick,1.8) + win * pick * 0.3;
		PriceDao dao = PriceDao.getInstance();
		dao.insertPrice(champion, price, win, pick);
		System.out.printf("price %s inserted\n", champion);
	}

	public double[] getAllNow() throws Exception {
		double[] ret = new double[140];

		PriceDao dao = PriceDao.getInstance();
		for (int i = 1; i <= 139; i++) {
			Price price = dao.getOnePrice(ChampionVO.id2nameEN[i]);
			if(price.getDate().getDay() != new Date().getDay())
				insertPrice(ChampionVO.id2nameEN[i]);
			ret[i] = price.getPrice();
		}
		return ret;
	}
}