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
   
   #viewtable {
   	 width : 800px;
     margin: 30px 0;
     td {
     	padding : 20px;
     }
    
   }
   
   #table {
  margin-bottom : 150px; 
  td {
     text-align : center;
     padding    : 10px;
     border     : 1px solid silver;
     
     &:nth-of-type(1) {
         width             :  150px;
         background-color  :  black;
         color             :  white;
     }
     &:nth-of-type(2) { width : 250px; }
     &:nth-of-type(3) {
         width             :  150px;
         background-color  :  black;
         color             :  white;
     }
     &:nth-of-type(4) { width : 250px;  }
  }  
  
  tr:nth-of-type(3) td:nth-of-type(2) {
     text-align : left;
  }
  
  tr:nth-of-type(4) td:nth-of-type(2) {
     height     : 250px;
     width      : 600px; 
     text-align : left;
     vertical-align: baseline;      
  }
  
  tr:last-of-type  td {
     background: white;
     color :     black; 
  }
   
 }
 
 /* class="btn btn-dark btn-sm" */
 a.btn.btn-dark.btn-sm:hover {
    text-decoration: none;    
 }
  
</style> 

</head>
<body>
  <main>	

    <!-- 메뉴 리스트 -->
    <%@include file="/WEB-INF/include/menus.jsp" %>
	
	<%-- <h2>${ menu_id }게시물 목록</h2> --%>
	<h2> ${ menuDTO.menu_name } 내용 보기</h2> 
	<%-- <h2>${ param.menu_id }게시물 목록</h2 --%>
	
	<!--  게시물 목록 -->
	<a href="/Board/List?menu_id=${boardDTO.menu_id }">목록으로</a>
	<table id = "table">		  	  
	  <tr>
	  	<td>글번호</td>
	    <td>${ boardDTO.idx      }</td>
	    <td>조회수</td>
	    <td>${ boardDTO.hit      }</td>
	  </tr>
	  <tr>
	  	<td>작성자</td>
	    <td>${ boardDTO.writer   }</td>
	    <td>작성일</td>
	    <td>${ boardDTO.regdate  }</td>	   
	  </tr>  
	  <tr>
	  	<td>제목</td>  
	    <td colspan="3">${ boardDTO.title }</td>	  	   
	  </tr>
	  <tr>
	  	<td>내용</td>
	    <td colspan="3" height="100px;">${ boardDTO.content }</td>	   
	  </tr>	  
	  <tr>
	  	<td colspan="4">
	  		[<a href="/Board/WriteForm?menu_id=${boardDTO.menu_id }">새글 쓰기</a>]&nbsp;&nbsp;
	  		[<a href="/Board/UpdateForm?idx=${boardDTO.idx }&menu_id=${boardDTO.menu_id}">수정</a>]&nbsp;&nbsp;
	  		[<a href="/Board/Delete?idx=${boardDTO.idx }&menu_id=${boardDTO.menu_id}">삭제</a>]&nbsp;&nbsp;
	  		[<a href="/Board/List?menu_id=${boardDTO.menu_id }">목록</a>]&nbsp;&nbsp;
	  		[<a href="/">Home</a>]&nbsp;&nbsp;	  	
	  	</td>
	  </tr>
	</table>
	
	<table id = "viewtable">		  	  
	  <tr>
	    <td>${ boardDTO.idx      }</td>
	    <td>
	    	${ boardDTO.title    }
	    </td>
	    <td>${ boardDTO.writer   }</td>
	 
	  	   
	    <td>${ boardDTO.regdate  }</td>	   
	    <td>${ boardDTO.hit      }</td>
	  </tr>
	  <tr>
	    <td colspan="5" height="500px;">${ boardDTO.content }</td>	   
	  </tr>	  
	</table>
	<a href="/Board/List?menu_id=${boardDTO.menu_id }">목록으로</a>
  </main>	
</body>
</html>




