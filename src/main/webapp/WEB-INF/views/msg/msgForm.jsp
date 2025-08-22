<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"     %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet"  href="/css/common.css" />
<!-- SCSS 문법가능하게하는 라이브러리 
<script src="https://cdn.jsdelivr.net/npm/browser-scss@1.0.3/dist/browser-scss.min.js"></script>
 -->

<style>
 
</style> 

</head>
<body>

	<form action = "/Msg/WriteMsg" method = "post">
		<input type = "hidden" name ="sender_id" value = "${sessionScope.login.userid }" />
		<input type = "text" name ="receiver_id" value = "admin0" readonly="readonly"/>
		<input type = "text" name = "title" />
		<input type = "text" name = "content" />
		<input type = "submit" value = "전송"/>
	</form>
</body>
</html>




