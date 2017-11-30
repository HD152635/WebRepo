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
	<div class="row">
		<form id="signupForm" class="register_form center-align">
			<h5>Riot stock</h5>
			<div>시작하려면 Riotstock에 가입하세요.</div>
			<div class="row">
				<div class="input-field s6">
					<input name="id" id="inputEmail" type="email" class="validate" autofocus>
					<label for="inputEmail" data-error="wrong" data-success="right" >email</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field s6">
					<input name="pwd" id="inputPwd" type="password" class="validate">
					<label for="inputPwd">password</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field s6">
					<input name="name" id="inputName" type="text" class="validate">
					<label for="inputName">name</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field s6">
					<input name="nickname" id="inputNickname" type="text" class="validate">
					<label for="inputNickname">nickname</label>
				</div>
			</div>
			<button class="btn waves-effect waves-light" type="submit"
				name="action" style="width: 100%">로그인</button>
		</form>
	</div>
	
	<%@ include file="modal.jsp"%>
	<script>
		$(document).ready(function() {
			$('#signupForm').submit(function(event) {
				// Stop form from submitting normally
				event.preventDefault();

				var id = $("#inputEmail").val();
				var pwd = $("#inputPwd").val();
				var name = $("#inputName").val();
				var nickname = $("#inputNickname").val();

				$.post("${contextPath}/signup.do", {
					"id" : id,
					"pwd" : pwd,
					"name" : name,
					"nickname" : nickname
				}, function(data) {
					if(data.msg == 'success') {
				    	location.href = "main.jsp";
				    } else if(data.msg == 'error') {
					  	var myModal = $('#modal1');
						myModal.find('.modal-title').text('Sign Up Error');
						myModal.find('.modal-body').text(data.error);
						myModal.modal();
						myModal.modal('open');
					}
				});
			});
		});
	</script>
</body>
</html>