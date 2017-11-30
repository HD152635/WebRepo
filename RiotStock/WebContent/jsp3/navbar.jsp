<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	<% request.setCharacterEncoding("EUC-KR"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- jsp-api.jar Ãß°¡ --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="sidebar.jsp"%>
<header>
<div class="navbar-fixed">
	<nav class="navbar-color">
	<div class="nav-wrapper row">
		<ul class="left">
			<li class=""><a href="${contextPath}/jsp3/stock.jsp" class="icon-container"><i
					class="large grey-text text-darken-1 material-icons">shopping_cart</i></a></li>
			<li class=""><a href="${contextPath}/jsp3/chart.jsp" class="icon-container"><i
					class="large grey-text text-darken-1 material-icons">show_chart</i></a></li>
			<li class=""><a href="#" class="icon-container"><i
					class="large grey-text text-darken-1 material-icons">money</i></a></li>
		</ul>
		<form action="${contextPath}/search">
			<div class="input-field">
				<input name="search" id="search" type="search" required> <label
					class="label-icon" for="search"><i
					class="grey-text material-icons">search</i></label>
			</div>
		</form>
		<ul class="right">
			<% if (user == null) { %><li><a href="#" onclick="onLogin();">login</a></li>
			<li>
				<div class="bar"></div>
			</li>
			<li><a href="${contextPath}/jsp3/signup.jsp">sign up</a></li>
			<% } else { %>
			<li>
				<a href="${contextPath}/logout.do">logout</a>
			</li>
			<% } %>
		</ul>
	</div>
	</nav>
</div>
<ul class="side-nav fixed">
	<li class="logo"><a
		class="icon-container waves-effect waves-light brand-logo" href="${contextPath}/jsp3/main.jsp"><i
			class="small material-icons" id="logo">adjust</i></a></li>
	<li class=""><a href="#" class="icon-container disabled"><i
			class="small material-icons"></i></a></li>
	<li class=""><a href="#" data-activates="slide-out1"
		class="button-collapse icon-container waves-effect waves-light"><i
			class="grey-text text-lighten-3 small material-icons">person</i></a></li>
	<li class=""><a href="#" data-activates="slide-out2"
		class="button-collapse icon-container waves-effect waves-light"><i
			class="grey-text text-lighten-3 small material-icons">business_center</i></a></li>
	<li class=""><a href="#"
		class="icon-container waves-effect waves-light"><i
			class="grey-text text-lighten-3 small material-icons">access_time</i></a></li>
</ul>
</header>
<script>
function onLogin(){
	  console.log('ho!');
	  $("a[data-activates=slide-out1]").sideNav('show');
}
</script>