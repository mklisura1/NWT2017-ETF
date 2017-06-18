<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<!DOCTYPE html>
<html lang="en">

<head>

<title>NWT | Login</title>

<!-- Import pocetnih skripti iz fajla -->
<%@ include file="page_tiles/start_skripts.jsp"%>


</head>
  <body class="hold-transition login-page">
    <div class="login-box">
      <div class="login-logo">
        <a href="login"><b>N</b>WT</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">      	
        <p class="login-box-msg">Sign in to start your session</p>
        <form action="${cp}/login" method="post">
          <div class="form-group has-feedback">
            <input type="text" class="form-control" placeholder="Username" name="username">
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
          </div>
          <div class="form-group has-feedback">
            <input type="password" class="form-control" placeholder="Password" name="password">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            <h5 style="color: red">${error}</h5>
          </div>
          <div class="row">
            <div class="col-xs-4">
              <button type="submit" class="btn btn-primary btn-block btn-flat" style="float: right;">Sign In</button>
            </div><!-- /.col -->
          </div>
        </form>
        <!-- <a href="#">I forgot my password</a><br> -->
        <a href="${cp}/register" class="text-center">Register a new membership</a>
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->
    
    <!-- Import krajnjih skripti iz fajla -->
	<%@ include file="page_tiles/end_skripts.jsp"%>
   
</body>
</html>