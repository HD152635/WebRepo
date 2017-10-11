<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "org.dimigo.vo.UserVO" %>

<!DOCTYPE html>
<html lang="ko">

<head>
  <title>main</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="/WebClass/css/main.css" />
</head>

<body>
  <div class="forGrid">
    <nav class="navbar navbar-default sidebar">
      <div class="container-fluid">
      	<div class="loginForm">
			<% UserVO user = (UserVO)session.getAttribute("user");
			if(user==null){ %>
          		<span class="largetxt">
        			Login
      			</span>
				<!-- <form id="loginForm" action="/WebClass/bloglogin" method="post">
            		<input type="email" name="id" id="inputEmail" class="form-control"
					placeholder="Email address" required>
    				<input type="password" name="pwd" id="inputPassword" class="form-control"
					placeholder="Password" required>
            		<button id="loginbtn" class="btn" type="submit" data-toggle="modal" data-target="#myModal">
    				Sign in</button>
         		</form> -->
         			<a href="/WebClass/bloglogin">
            			<button class="btn" type="button">Sign in</button>
          			</a>
    				<a href="#">
            			<button class="btn" type="button">Register</button>
          			</a>
    		<% } else { %>
	    		<span class="largetxt">
		    		<%= user.getName()%>년
		      	</span>
		      	<div class="dropdown-menu dropdown-menu-right" aria-labelledby="bd-versions">
	        		<button type="button" class="dropdown-item">Action1</button>
	        		<button type="button" class="dropdown-item">Action2</button>
	      		</div>
    			<form id="LoginForm" action = "/WebClass/bloglogout" method = "post">
    				<button class="btn" type="submit">Sign out</button>
    			</form>
			<% } %>
        </div>
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
        </div>
        <div class="collapse navbar-collapse" id="collapse-1">
          <ul class="nav navbar-nav" id="side">
            <!--<li><a href="#">Profile<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-user"></span></a></li>-->
            <li><a href="/WebClass/myblog/main.html">Home<span style="font-size:16px;" class="pull-right hidden-xs showopacity glyphicon glyphicon-home"></span></a></li>
            <li><a href="/WebClass/myblog/office.html">Office<span style="font-size:16px;" class="pull-right showopacity glyphicon glyphicon-briefcase
        "></span></a></li>
            <li><a href="/WebClass/myblog/vacation.html">Vacation<span style="font-size:16px;" class="pull-right showopacity glyphicon glyphicon-globe"></span></a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="content">
      <div class="rst-content">
        <img src="/WebClass/images/haus.png" id="home" alt="home" />
        <h1>Let me Introduce My Home</h1>
        <h4>My home looks so luxury and has large spaces</h4>
        <button id="explore">Explore House</button>
        <div class="subtitle">My house has everything that you can think</div>
        <div class="intro-home container-fluid">
          <div class="col-sm-4">
            <i style="font-size:64px;" class="glyphicon glyphicon-user"></i>
            <h5>
              <strong>Professional Employee</strong>
            </h5>
            <p class="text-muted">
              First, many professional exployee has worked in house. The house was managed to them.
            </p>
          </div>
          <div class="col-sm-4">
            <i style="font-size:64px;" class="glyphicon glyphicon-film"></i>
            <h5>
              <strong>Prviate theater</strong>
            </h5>
            <p class="text-muted">
              Have you been hoped that you can watch the latest movie in your house with Big screen? It can be possible in my house.
            </p>
          </div>
          <div class="col-sm-4">
            <i style="font-size:64px;" class="glyphicon glyphicon-plane"></i>
            <h5>
              <strong>Private plane</strong>
            </h5>
            <p class="text-muted">
              I can also ride a plane at anytime I want. It can fly up to the speed of sound.
            </p>
          </div>
        </div>
        <div class="subtitle">House view</div>
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
          <!-- Indicators -->
          <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
          </ol>
          <div class="carousel-inner">
            <div class="item active">
              <img src="/WebClass/images/luxuryhome2.jpg" alt="3" style="width:100%;">
            </div>
            <div class="item">
              <img src="/WebClass/images/luxuryhome1.jpg" alt="1" style="width:100%;">
            </div>
          </div>
          <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
          <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
        </div>
      </div>
    </div>
  </div>

  <script src="/WebClass/js/main.js"></script>
</body>

</html>
