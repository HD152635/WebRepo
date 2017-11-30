<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="org.dimigo.vo.ChampionVO"%>
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
<% ChampionVO champ = (ChampionVO) request.getAttribute("champion"); %>
<c:set var="rank" value="${requestScope.rank}"/>
<c:set var="price" value="${requestScope.price}"/>
	<%@ include file="navbar.jsp"%>
	<div class="champion_intro_header row">
    <div class="champion_stat">
      <a class="waves-effect waves-light btn"  onclick="location.reload(true)">
        <i class="material-icons">cached</i>
        <p class="variation">
        </p>
      </a>
    </div>
    <div class="champion_intro">
      <div class="intro_champion_img">
        <img src="champion/<%= champ.getNameEN() %>.png" width="140px" />
      </div>
      <h1 class="champion_name"><%= champ.getNameKR() %></h1>
      <div class="champion_intro_ranking">
        챔피언 주가 랭킹 ${rank}위
      </div>
      <ul class="champion_intro_ratio">
        <li>
          <div class="champion_intro_winr">
            승률 : <span class="win_ratio"><fmt:formatNumber value="${win}" pattern="0.00"/> %</span>
          </div>
        </li>
        <li>
          <div class="champion_intro_winr">
            픽률 : <span class="pick_ratio"><fmt:formatNumber value="${pick}" pattern="0.00"/>%</span>
          </div>
        </li>
        <li>
          <div class="champion_intro_price">
            현재 가치 : <span class="price"><fmt:formatNumber value="${price}" pattern="0.000000"/></span>
          </div>
        </li>
      </ul>
    </div>
  </div>
  <div class="row" style="min-height: 900px">
    <div class="main-content-container">
      <div class="main-content">
        <h1><strong class="champion_name"><%= champ.getNameKR() %></strong> 거래하기</h1>
        <div class="col s7 table_trading_status">
          <table class="table_trading centered">
            <thead>
              <tr>
                <th class="tb_sell">매도주문</th>
                <th class="tb_sell">매도잔량</th>
                <th>가격</th>
                <th class="tb_buy">매수잔량</th>
                <th class="tb_buy">매수주문</th>
              </tr>
            </thead>
            <tbody>
              <c:forEach var="i" begin="1" end="4">
	              <tr class="tb_sell">
	                <td></td>
	                <td class="t_amount"></td>
	                <td class="t_1bit"></td>
	                <td class="empty_cell"></td>
	                <td class="empty_cell"></td>
	              </tr>
              </c:forEach>
              <c:forEach var="i" begin="1" end="4">
	              <tr class="tb_buy">
	                <td class="empty_cell"></td>
	                <td class="empty_cell"></td>
	                <td class="t_1bit"></td>
	                <td class="t_amount"></td>
	                <td></td>
	              </tr>
              </c:forEach>
            </tbody>
          </table>
        </div>
        <div class="order_tab col s5">
          <ul class="tabs">
            <li class="tab col s6 tb_sell"><a class="" href="#order_sell">매도</a></li>
            <li class="tab col s6 tb_buy"><a href="#order_buy">매수</a></li>
          </ul>
          <form id="order_sell" class="col s12">
            <div class="input-field">
              <input name="share" placeholder="소수점 두자리까지 인식됩니다." id="sell_quantity" type="text" class="validate">
              <label for="sell_quantity">주문 수량</label>
            </div>
            <p class="range-field">
              <label for="sell_price">주문 가격</label>
              <input name="price" type="range" id="sell_price" min="${price*0.7}" max="${price*1.3}" />
            </p>
            <button style="background: #FF6B72;" class="btn waves-effect waves-light" type="submit">매도</button>
          </form>
          <form id="order_buy" class="col s12">
            <div class="input-field">
              <input name="share" placeholder="소수점 두자리까지 인식됩니다." id="buy_quantity" type="text" class="validate">
              <label for="buy_quantity">주문 수량</label>
            </div>
            <p class="range-field">
              <label for="buy_price">주문 가격</label>
              <input name="price" type="range" id="buy_price" min="${price*0.7}" max="${price*1.3}" />
            </p>
            <button style="background: #3A88C0;" class="btn waves-effect waves-light" type="submit">매수</button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <%@ include file="modal.jsp" %>
  	<script>
  	$(".modal-close").click(function(event){
  		location.reload(true);
  	})
  	$('form#order_sell').submit(function(event){
  		event.preventDefault();

		var share = document.getElementById('sell_quantity').value;
		var price = document.getElementById('sell_price').value;
        var champion = "<%= champ.getNameEN() %>";
		$.post("${contextPath}/stock/sale",{
			"share": share,
			"price": price,
			"champion": champion
        }, function(data){
           	if(data.msg == 'success') {
           		var myModal = $('#modal1');
				myModal.find('.modal-title').text('매도완료');
				myModal.modal();
				myModal.modal('open');
		    } else if(data.msg == 'error') {
			  	var myModal = $('#modal1');
				myModal.find('.modal-title').text('Upload Stock Error');
				myModal.find('.modal-body').text(data.error);
				myModal.modal();
				myModal.modal('open');
			}
       	});
  	});
  	$('form#order_buy').submit(function(event){
  		event.preventDefault();

		var share = document.getElementById('buy_quantity').value;
		var price = document.getElementById('buy_price').value;
        var champion = "<%= champ.getNameEN() %>";
        
		$.post("${contextPath}/stock/purchase",{
			"share": share,
			"price": price,
			"champion": champion
        }, function(data){
           	if(data.msg == 'success') {
           		var myModal = $('#modal1');
				myModal.find('.modal-title').text('매수완료');
				myModal.modal();
				myModal.modal('open');
		    } else if(data.msg == 'error') {
			  	var myModal = $('#modal1');
				myModal.find('.modal-title').text('Upload Stock Error');
				myModal.find('.modal-body').text(data.error);
				myModal.modal();
				myModal.modal('open');
			}
       	});
  	});
	$(document).ready(function() {
		var champion = "<%=champ.getNameEN()%>";
		$.getJSON("${contextPath}/stocklist?champion=" + champion
				+ "&kind=purchase&limit=4", function(data) {
			$.each(data, function(index, item) {
				$(".tb_buy .t_1bit")[index].innerHTML = ((item.price).toFixed(3));
				$(".tb_buy .t_amount")[index].innerHTML = ((item.share).toString());
			});
		});
		$.getJSON("${contextPath}/stocklist?champion=" + champion
				+ "&kind=sale&limit=4", function(data) {
			$.each(data, function(index, item) {
				$(".tb_sell .t_1bit")[index].innerHTML = ((item.price).toFixed(3));
				$(".tb_sell .t_amount")[index].innerHTML = ((item.share).toString());
			});
		});
	});
	</script>
</body>
</html>