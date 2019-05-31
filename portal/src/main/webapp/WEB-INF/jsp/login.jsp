<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
        <meta name="description" content="">
        <meta name="author" content="">
        <!--<link rel="icon" href="https://v3.bootcss.com/favicon.ico">-->

        <title>Signin Template for Bootstrap</title>

        <!-- Bootstrap core CSS -->
        <link href="../portal/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="../portal/static/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="../portal/static/css/signin.css" rel="stylesheet">
        <script src="../portal/static/bootstrap/js/jquery.min.js"></script>
        <script src="../portal/static/bootstrap/js/bootstrap.min.js"></script>

  </head>

  <body>

        <div class="container">

          <form class="form-signin">
            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="">
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
            <div class="checkbox">
              <label>
                <input type="checkbox" value="remember-me"> Remember me
              </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
          </form>
        </div>

  </body>
</html>