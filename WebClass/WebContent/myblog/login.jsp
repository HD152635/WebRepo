<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>LogIn</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">
<link rel="stylesheet" href="/WebClass/css/signin.css">
</head>

<body>
	<div class="container">

		<form id="signupForm" class="form-signin" action="" method="post">
			<h2 class="form-signin-heading">Please sign in</h2>

			<label for="inputEmail" class="sr-only">Email address</label> <input
				type="email" name="id" id="inputEmail" class="form-control"
				placeholder="Email address" required autofocus> <label
				for="inputPassword" class="sr-only">Password</label> <input
				type="password" name="pwd" id="inputPassword" class="form-control"
				placeholder="Password" required>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
				in</button>
		</form>

	</div>

	<%@ include file="modal.jsp"%>

	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
		integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
		integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
		crossorigin="anonymous"></script>

	<script>
		
	<%-- 로그인이 실패한 경우 처리 추가 --%>
		
	<%if ("error".equals(request.getAttribute("msg"))) {%>
		var myModal = $('#myModal');
		myModal.find('.modal-title').text('Login Error');
		myModal.find('.modal-body').text('Invalid username or password');
		myModal.modal();
	<%}%>

    $(document).ready(function () {
        $('#signupForm').submit(function (event) {
           event.preventDefault();
         var email = $('#inputEmail').val();
         var pwd = $('#inputPassword').val();
         var result;
         console.log(email,pwd);
         $.post("/WebClass/bloglogin",
               {"email" : email, "pwd" : pwd, "result" : result},
               function(data) {
                  console.log(data);
                  var d = JSON.parse(data)
                  if(d.result){
                     location.href='myblog/home.jsp';   
                  }
                  else{
                     console.log(d.result,d.pwd,d.email);
                     var myModal = $('#myModal');
                     myModal.find('.modal-title').text('Sign Up Error');
                     myModal.find('.modal-body').text('회원 가입 시 오류가 발생하였습니다.');
                     myModal.modal();
                     $('input[name="pwd"]').val('');
                  }
             });
        });
     });
	</script>

</body>
</html>