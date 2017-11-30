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
public class ChampionVO {
	private int id;
	private String nameEN;
	private String nameKR;
	public static String[] id2nameEN = { "", "Garen", "Galio", "Gangplank", "Gragas", "Graves", "Gnar", "Nami", "Nasus",
			"Nautilus", "Nocturne", "Nunu", "Nidalee", "Darius", "Diana", "Draven", "Ryze", "Rakan", "Rammus", "Lux",
			"Rumble", "Renekton", "Leona", "RekSai", "Rengar", "Lucian", "Lulu", "Leblanc", "LeeSin", "Riven",
			"Lissandra", "MasterYi", "Maokai", "Malzahar", "Malphite", "Mordekaiser", "Morgana", "DrMundo",
			"MissFortune", "Bard", "Varus", "Vi", "Veigar", "Vayne", "Velkoz", "Volibear", "Braum", "Brand", "Vladimir",
			"Blitzcrank", "Viktor", "Poppy", "Sion", "Shaco", "Sejuani", "Sona", "Soraka", "Shen", "Shyvana", "Swain",
			"Skarner", "Sivir", "XinZhao", "Syndra", "Singed", "Thresh", "Ahri", "Amumu", "AurelionSol", "Ivern",
			"Azir", "Akali", "Aatrox", "Alistar", "Annie", "Anivia", "Ashe", "Yasuo", "Ekko", "Elise", "Wukong", "Ornn",
			"Orianna", "Olaf", "Yorick", "Udyr", "Urgot", "Warwick", "Irelia", "Evelynn", "Ezreal", "Illaoi",
			"JarvanIV", "Xayah", "Zyra", "Zac", "Janna", "Jax", "Zed", "Xerath", "Jayce", "Zoe", "Ziggs", "Jhin",
			"Zilean", "Jinx", "Chogath", "Karma", "Camille", "Kassadin", "Karthus", "Cassiopeia", "Khazix", "Katarina",
			"Kalista", "Kennen", "Caitlyn", "Kayn", "Kayle", "KogMaw", "Corki", "Quinn", "Kled", "Kindred", "Taric",
			"Talon", "Taliyah", "TahmKench", "Trundle", "Tristana", "Tryndamere", "TwistedFate", "Twitch", "Teemo",
			"Pantheon", "Fiddlesticks", "Fiora", "Fizz", "Heimerdinger", "Hecarim" };
	public static String[] id2nameKR = { "", "가렌", "갈리오", "갱플랭크", "그라가스", "그레이브즈", "나르", "나미", "나서스", "노틸러스", "녹턴",
			"누누", "니달리", "다리우스", "다이애나", "드레이븐", "라이즈", "라칸", "람머스", "럭스", "럼블", "레넥톤", "레오나", "렉사이", "렝가", "루시안", "룰루",
			"르블랑", "리 신", "리븐", "리산드라", "마스터 이", "마오카이", "말자하", "말파이트", "모데카이저", "모르가나", "문도 박사", "미스 포츈", "바드", "바루스",
			"바이", "베이가", "베인", "벨코즈", "볼리베어", "브라움", "브랜드", "블라디미르", "블리츠크랭크", "빅토르", "뽀삐", "사이온", "샤코", "세주아니", "소나",
			"소라카", "쉔", "쉬바나", "스웨인", "스카너", "시비르", "신 짜오", "신드라", "신지드", "쓰레쉬", "아리", "아무무", "아우렐리온 솔", "아이번", "아지르",
			"아칼리", "아트록스", "알리스타", "애니", "애니비아", "애쉬", "야스오", "에코", "엘리스", "오공", "오른", "오리아나", "올라프", "요릭", "우디르",
			"우르곳", "워윅", "이렐리아", "이블린", "이즈리얼", "일라오이", "자르반 4 세", "자야", "자이라", "자크", "잔나", "잭스", "제드", "제라스", "제이스",
			"조이", "직스", "진", "질리언", "징크스", "초가스", "카르마", "카밀", "카사딘", "카서스", "카시오페아", "카직스", "카타리나", "칼리스타", "케넨",
			"케이틀린", "케인", "케일", "코그모", "코르키", "퀸", "클레드", "킨드레드", "타릭", "탈론", "탈리야", "탐 켄치", "트런들", "트리스타나", "트린다미어",
			"트위스티드 페이트", "트위치", "티모", "판테온", "피들스틱", "피오라", "피즈", "하이머딩거", "헤카림" };

	public ChampionVO(int id) {
		this.id = id;
		this.nameEN = id2nameEN[id];
		this.nameKR = id2nameKR[id];
	}

	public ChampionVO(String name) {
		int i=0;
		while(!id2nameEN[++i].equals(name) && !id2nameKR[i].equals(name));
		this.id = i;
		this.nameEN = id2nameEN[i];
		this.nameKR = id2nameKR[i];
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getNameKR() {
		return nameKR;
	}

	public void setNameKR(String nameKR) {
		this.nameKR = nameKR;
	}

	@Override
	public String toString() {
		return "ChampionVO [id=" + id + ", nameEN=" + nameEN + ", nameKR=" + nameKR + "]";
	}
}
