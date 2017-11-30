<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- jsp-api.jar 추가 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ page import="org.dimigo.vo.ChampionVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
  <!--Import Google Icon Font-->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <!--Import materialize.css-->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
  <link type="text/css" rel="stylesheet" href="${contextPath}/css/main.css">
  <!--Let browser know website is optimized for mobile-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
</head>
<style>
  body,
  html {
    height: 100%;
    background-color: #000;
    color: #fff;
  }
</style>

<body>
  <header>
    <nav class="chart_header">
      <div class="nav-wrapper white-text">
        <a href="${contextPath}/jsp3/main.jsp" class="brand-logo">RiotStock</a>
        <ul id="nav-mobile" class="right">
          <li><a href="${contextPath}/jsp3/stock.jsp">거래하기</a></li>
          <c:if test="${user == null}">
          	<li><a href="${contextPath}/jsp3/main.jsp">로그인</a></li>
          </c:if>
        </ul>
      </div>
    </nav>
  </header>
  <div id="chartdiv"></div>
  <div class="chat_win">
    <div class="chat_header">

    </div>
    <div class="chat_list_wrapper">
      <ul class="chat_list">
        <li class="chat_item">
          <div class="time">18:01</div>
          <div class="message_wrapper">
            <span class="nickname">the tkxks</span>
            <span class="message">안녕 난 선웅이라고 해</span>
          </div>
        </li>
      </ul>
    </div>
    <div class="chat_input">
      <form class="input-field">
        <textarea placeholder="뭐라도 적어봐" id="input_text" class="materialize-textarea" name="input_text"></textarea>
      </form>
    </div>
  </div>
  <script src="https://www.amcharts.com/lib/3/amcharts.js"></script>
  <script src="https://www.amcharts.com/lib/3/serial.js"></script>
  <script src="https://www.amcharts.com/lib/3/amstock.js"></script>
  <script src="http://www.amcharts.com/lib/3/plugins/dataloader/dataloader.min.js" type="text/javascript"></script>
  <script src="https://www.amcharts.com/lib/3/plugins/export/export.min.js"></script>
  <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
  <script src="https://www.amcharts.com/lib/3/themes/black.js"></script>
  <script>
  var chart = AmCharts.makeChart( "chartdiv", {
	  "type": "stock",
	  "theme": "black",
	  "dataSets": [
		<%
		for (int i = 1; i <= 139; i++) {
			ChampionVO champion = new ChampionVO(i);
		%>	{
		      "title": "<%=champion.getNameEN()%>",
		      "fieldMappings": [ {
		        "fromField": "value",
		        "toField": "value"
		      }, {
		        "fromField": "volume",
		        "toField": "volume"
		      } ],
		      "dataLoader":{
		    	  url: "${contextPath}/translist?champion=<%=champion.getNameEN()%>&limit=500"
		      },
		      "categoryField": "date"
		    },
		 <% } %>
	  ],

	  "panels": [ {
	    "showCategoryAxis": false,
	    "title": "Value",
	    "percentHeight": 70,
	    "stockGraphs": [ {
	      "id": "g1",
	      "valueField": "value",
	      "comparable": true,
	      "compareField": "value",
	      "balloonText": "[[title]]:<b>[[value]]</b>",
	      "compareGraphBalloonText": "[[title]]:<b>[[value]]</b>"
	    } ],
	    "stockLegend": {
	      "periodValueTextComparing": "[[percents.value.close]]%",
	      "periodValueTextRegular": "[[value.close]]"
	    }
	  }, {
	    "title": "Volume",
	    "percentHeight": 30,
	    "stockGraphs": [ {
	      "valueField": "volume",
	      "type": "column",
	      "showBalloon": false,
	      "fillAlphas": 1
	    } ],
	    "stockLegend": {
	      "periodValueTextRegular": "[[value.close]]"
	    }
	  } ],

	  "chartScrollbarSettings": {
	    "graph": "g1"
	  },

	  "chartCursorSettings": {
	    "valueBalloonsEnabled": true,
	    "fullWidth": true,
	    "cursorAlpha": 0.1,
	    "valueLineBalloonEnabled": true,
	    "valueLineEnabled": true,
	    "valueLineAlpha": 0.5
	  },

	  "periodSelector": {
	    "position": "left",
	    "periods": [ {
	      "period": "MM",
	      "selected": true,
	      "count": 1,
	      "label": "1 month"
	    }, {
	      "period": "YYYY",
	      "count": 1,
	      "label": "1 year"
	    }, {
	      "period": "YTD",
	      "label": "YTD"
	    }, {
	      "period": "MAX",
	      "label": "MAX"
	    } ]
	  },

	  "dataSetSelector": {
	    "position": "left"
	  },

	  "export": {
	    "enabled": true
	  }
	} );

  </script>
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
  <script src="http://cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
  <script src="${contextPath}/js/main.js"></script>
  <script>
  $(document).ready(function(){
	  /* 시작될때 채팅목록 탐색 (20) */
	  $.post("${contextPath}/searchChat", {
			"date": "2015-08-19 12:31:22",
			"limit": 20
		}, function(data) {
			$.each(data, function(index, item){
				console.log(item);
				$(".chat_list").append("<li class=\"chat_item\">"
				          + "<div class=\"time\">"+item.date+"</div>"
		          		  + "<div class=\"message_wrapper\">"
		            	  + "<span class=\"nickname\">" + item.userId +"</span>"
		            	  +	"<span class=\"message\">"+ item.text +"</span></div></li>");
			});
		});
  })
  $("#input_text").keydown(MessageTextOnKeyEnter);
  function MessageTextOnKeyEnter(e)
  {
     if (e.keyCode == 13) {
       console.log(e.keyCode);
         if (e.ctrlKey) {
             SendMessage();
         }
         return false;
     }
  }
  function SendMessage(){
	  /* 메세지보내기 */
	  var text = $("#input_text").val();

		$.post("${contextPath}/insertChat", {
			"text": text
		}, function(data) {
			if(data.msg == 'success'){
				$.post("${contextPath}/searchChat", {
					"date": $(".time").last().html(),
					"limit": 20
				}, function(data) {
					$.each(data, function(index, item){
						console.log(item);
						$(".chat_list").append("<li class=\"chat_item\">"
						          + "<div class=\"time\">"+item.date+"</div>"
				          		  + "<div class=\"message_wrapper\">"
				            	  + "<span class=\"nickname\">" + item.userId +"</span>"
				            	  +	"<span class=\"message\">"+ item.text +"</span></div></li>");
					});
				});
			} else if(data.msg == 'error'){
				console.log('error');
			}
			$("#input_text").val("");
		});
  }
  </script>
</body>

</html>