/**
 * 
 */
package org.dimigo.vo;

/**
 * <pre>
 * org.dimigo.vo
 *  |_ User
 * 
 * 1. 개요 :
 * 2. 작성일 : 2017. 9. 21.
 * </pre>
 *
 * @author : teacher
 * @version : 1.0
 */
public class UserVO {
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private String champion;
	private double[] stockInfo=new double[140];
	private double money;
	
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public UserVO() {
		
	}
	
	/**
	 * @param id
	 * @param name
	 * @param nickName
	 */
	public UserVO(String id, String pwd, String name, String nickname, String champion, double money) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nickname = nickname;
		this.champion = champion;
		this.money = money;
	}
	public String getChampion() {
		return champion;
	}

	public void setChampion(String champion) {
		this.champion = champion;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	public double[] getStockInfo() {
		return stockInfo;
	}
	public double getStockInfoByIdx(int idx) {
		return stockInfo[idx];
	}

	public void setStockInfo(double[] stockInfo) {
		this.stockInfo = stockInfo;
	}
	public void setStockInfo(String stockInfo) {
		String strs[] = stockInfo.split(",");
		for(int i=0;i<strs.length;i++)
			this.stockInfo[i] = Double.parseDouble(strs[i]);
	}
	public String getStockInfoStr() {
		String[] s = new String[stockInfo.length];

		for (int i = 0; i < s.length; i++)
		    s[i] = String.valueOf(stockInfo[i]);
		return String.join(",", s);
		
	}
	public void setStockInfoByIdx(int idx, double share) {
		this.stockInfo[idx] = share;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the nickName
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", nickname=" + nickname + "]";
	}

}
