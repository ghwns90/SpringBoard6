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

</head>
<body>
  <main>
	<h2>사용자 목록</h2>
	<table>	  
	  <tr>
	    <td> 
	    </td>
	  </tr>
	
	  <c:forEach  var="msg" items = "${ msgList }" >
	  <tr>
	    <td>${ msg.id    }</td>
	    <td>${ msg.title  }</td>
	    <td>${ msg.content     }</td>	   
	    <td>${ msg.sender_id  }</td>	   
	    <td>${ msg.receiver_id    }</td>	   
	  </tr>
	  </c:forEach>
	  
	</table>
  </main>	
</body>
</html>




