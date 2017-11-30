<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.dimigo.vo.ChampionVO"%>
<%@ page import="java.util.*"%>
<%-- jsp-api.jar 추가 --%>
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
	<script>
		onChampion = "";
		selected = new Array();
		function toggle_active(self) {
			if (self.classList.contains("active")) {
				self.classList.remove('active');
				var i = selected.indexOf(self.alt);
				if(i != -1) {
					selected.splice(i, 1);
				}
			} else {
				/* 해당 거래 내역 초기화 */
				$("#tab_cplt .td .t_1bit").text('-');
				$("#tab_cplt .td .t_amount").text('-');
				$("#tab_cplt .td .t_price").text('-');
				$("#tab_purchase .td .t_1bit").text('-');
				$("#tab_purchase .td .t_amount").text('-');
				$("#tab_purchase .td .t_price").text('-');
				$("#tab_sell .td .t_1bit").text('-');
				$("#tab_sell .td .t_amount").text('-');
				$("#tab_sell .td .t_price").text('-');
				onChampion = self.alt;
				$(".table_champion").html(onChampion);
				selected.push(onChampion);
				$.getJSON("${contextPath}/stocklist?champion=" + onChampion
						+ "&kind=completed&limit=10", function(data) {
					/* 해당 챔피언 거래내역 체크*/
					$.each(data, function(index, item) {
						$("#tab_cplt .td .t_1bit")[index].innerHTML = (Math.round(item.price).toString());
						$("#tab_cplt .td .t_amount")[index].innerHTML = (Math.round(item.share).toString());
						$("#tab_cplt .td .t_price")[index].innerHTML = (Math.round(item.price*item.share).toString());
					});
				});$.getJSON("${contextPath}/stocklist?champion=" + onChampion
						+ "&kind=purchase&limit=10", function(data) {
					$.each(data, function(index, item) {
						$("#tab_purchase .td .t_1bit")[index].innerHTML = (Math.round(item.price).toString());
						$("#tab_purchase .td .t_amount")[index].innerHTML = (Math.round(item.share).toString());
						$("#tab_purchase .td .t_price")[index].innerHTML = (Math.round(item.price*item.share).toString());
					});
				});$.getJSON("${contextPath}/stocklist?champion=" + onChampion
						+ "&kind=sale&limit=10", function(data) {
					$.each(data, function(index, item) {
						$("#tab_sell .td .t_1bit")[index].innerHTML = (Math.round(item.price).toString());
						$("#tab_sell .td .t_amount")[index].innerHTML = (Math.round(item.share).toString());
						$("#tab_sell .td .t_price")[index].innerHTML = (Math.round(item.price*item.share).toString());
					});
				});
				self.classList.add('active');
			}
		}
	</script>
	<div class="container" style="min-width:1200px;">
		<div class="row">
			<div class="box-container">
				<div class="col s7">
					<ul class="tabs">
						<li class="tab"><a href="#tab_all">전체</a></li>
						<li class="tab"><a href="#tab_top">탑</a></li>
						<li class="tab"><a href="#tab_mid">미드</a></li>
						<li class="tab"><a href="#tab_jungle">정글</a></li>
					</ul>
					<div id="tab_all" class="champions_container col s12">
						<%
						/* 모든 챔피언에 대해서 탐색 */
							for (int i = 1; i <= 139; i++) {
								ChampionVO champion = new ChampionVO(i);
						%>
						<div class="champion_container">
							<image
								src="${contextPath}/champion/<%= champion.getNameEN() %>.png"
								onclick="toggle_active(this);" alt="<%=champion.getNameEN()%>" />
							<span><%=champion.getNameKR()%></span>
						</div>
						<%
							}
						%>
					</div>
					<div id="tab_mid" class="col s8">안녕</div>
					<div id="tab_top" class="col s8">안녕</div>
					<div id="tab_jungle" class="col s8">안녕</div>
				</div>
				<div class="col s5">
					<ul class="tabs right">
						<li>
							<h2 class="table_champion"></h2>
						</li>
						<li class="tab"><a href="#tab_cplt">거래완료</a></li>
						<li class="tab"><a href="#tab_sell">판매</a></li>
						<li class="tab"><a href="#tab_purchase">구매</a></li>
					</ul>
					<div id="tab_cplt">
						<div class="c_table">
							<div class="tr">
								<div class="thead">
									<div class="t_sharp">#</div>
									<div class="t_1bit">1주당 가격</div>
									<div class="t_price">체결 가격</div>
									<div class="t_amount">거래량</div>
								</div>
							</div>
							<div class="tbody">
								<c:forEach var="i" begin="1" end="10">
									<div class="td">
										<div class="t_sharp">${i}</div>
										<div class="t_1bit">-</div>
										<div class="t_price">-</div>
										<div class="t_amount">-</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div id="tab_purchase">

						<div class="c_table">
							<div class="tr">
								<div class="thead">
									<div class="t_sharp">#</div>
									<div class="t_1bit">1주당 가격</div>
									<div class="t_price">매수 가격</div>
									<div class="t_amount">거래량</div>
								</div>
							</div>
							<div class="tbody">
								<c:forEach var="i" begin="1" end="10">
									<div class="td">
										<div class="t_sharp">${i}</div>
										<div class="t_1bit">-</div>
										<div class="t_price">-</div>
										<div class="t_amount">-</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div id="tab_sell">
						<div class="c_table">
							<div class="tr">
								<div class="thead">
									<div class="t_sharp">#</div>
									<div class="t_1bit">1주당 가격</div>
									<div class="t_price">매도 가격</div>
									<div class="t_amount">거래량</div>
								</div>
							</div>
							<div class="tbody">
								<c:forEach var="i" begin="1" end="10">
									<div class="td">
										<div class="t_sharp">${i}</div>
										<div class="t_1bit">-</div>
										<div class="t_price">-</div>
										<div class="t_amount">-</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div id="chart_multiple"></div>
		</div>
	</div>
	<script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
	<script src="https://www.amcharts.com/lib/3/serial.js"></script>
	<script src="https://www.amcharts.com/lib/3/amstock.js"></script>
	<script
		src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
	<link rel="stylesheet"
		href="https://www.amcharts.com/lib/3/plugins/export/export.css"
		type="text/css" media="all" />
	<script src="${contextPath}/js/mchart.js"></script>
</body>
</html>