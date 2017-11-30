<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- jsp-api.jar Ãß°¡ --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--Import Google Icon Font-->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<!--Import materialize.css-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<link type="text/css" rel="stylesheet"
	href="${contextPath}/css/main.css">
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<script src="${contextPath}/js/main.js"></script>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<% 
		List<ChampionVO> champions = (ArrayList<ChampionVO>) request.getAttribute("champions");
		if(champions == null){
			champions = new ArrayList<ChampionVO>();
			for(int i=1;i<=139;i++){
				ChampionVO champion = new ChampionVO(i);
				champions.add(champion);
			}
		}
	%>
	<div class="main-container">
		<div class="row list_header">
			<h5 style="font-weight: bold; font-size: 1.3rem;">champion list</h5>
		</div>
		<ul class="row cards-container">
			<%
			for (int i = 0; i < champions.size() ; i++) {
				ChampionVO champion = champions.get(i);
		%>
			<li class="card">
				<div class="card-content">
					<div class="card_champion_image" style="background-image: url('${contextPath}/champion/<%=champion.getNameEN()%>.png');"></div>
					<h4><%=champion.getNameKR()%></h4>
					<!--  <h5>Mid</h5>  -->
					<div class="chart-container">
						<div class="ch-chart" id="chart-<%=champion.getNameEN()%>"></div>
						<div class="price_up">
							<i id="chart-icon-<%=champion.getNameEN()%>" class="material-icons"></i><br /> <span id="chart-variation-<%=champion.getNameEN()%>"></span>
						</div>
					</div>
				</div>
				<div class="card-action">
					<a href=""><i class="grey-text lighten-2 material-icons small">star_border</i></a>
					<a href="${contextPath}/trans?champion=<%=champion.getNameEN()%>"><i class="grey-text lighten-2 material-icons small">shopping_cart</i></a>
				</div>
			</li>
		<% } %>
		</ul>
	</div>
	<link rel="stylesheet" href="http://cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
  <script src="http://cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
	<script>
	$(document).ready(function() {
		selected=new Array();
		<%
		for (int i = 0; i < champions.size() ; i++) {
			ChampionVO champion = champions.get(i);
		%>
			selected.push("<%=champion.getNameEN()%>");
		<% } %>
		selected.forEach(function(champion, index){
			$.getJSON("${contextPath}/pricelist?champion=" + champion
					+ "&limit=30", function(data) {
				prices = new Array();
				dates = new Array();
				$.each(data, function(index, item) {
					prices.push(Math.round(item.price));
					dates.push(item.date)
				});
				new Chartist.Line('#chart-'+champion+'',{
					  labels: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30],
					  series: [
					    prices
					  ]
					}, {
					  high: 200,
					  low: 0,
					  showArea: false,
					  showLine: true,
					  showPoint: false,
					  fullWidth: true,
					  axisX: {
					    showLabel: false,
					    offset: 0,
					    showGrid: false
					  },
					  axisY: {
					    showLabel: false,
					    offset: 0,
					    showGrid: true
					  }
					});
				if(prices[0]>prices[29]){
					$('#chart-icon-'+champion).html('arrow_drop_down');
				} else {
					$('#chart-icon-'+champion).html('arrow_drop_up');
				}
				$('#chart-variation-'+champion).html((Math.abs(prices[29]-prices[0])/prices[0]).toFixed(2)+'%');
			});
		});
	});
	</script>
</body>
</html>