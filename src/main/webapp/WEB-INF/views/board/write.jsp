<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/ico" href="/img/favicon.ico" />
<link rel="stylesheet"  href="/css/common.css" />

<style>

 #table {
  td {
     padding : 10px;
  }
  td:nth-of-type(1) { 
     text-align: center;  
     width : 200px; 
     background : #666;
     color : white;
     font-weight: bold;   
     border : 1px solid black;    
  } 
  td:nth-of-type(2) { width : 600px; }
  
  tr:last-child > td { 
      background: white; 
      border: 1px solid black
  }
  
  input                  { width : 100%; height : 35px; } 
  input[type=submit]     { width : 100px;  } 
  input[name=userid]     { width : 25%;  } 
  #goList                { width : 100px;  } 
  #dupCheck, #dupCheck2  { width : 100px; }
  
  .red                   { color:red; }
  .green                 { color:green; }
  textarea               {
      height : 200px;
      width  : 100%;      
  }
 }

</style>

</head>
<body>
  <main>
    
    <!-- 메뉴 리스트 -->
    <%@include file="/WEB-INF/include/menus.jsp" %>
  
	<h2>${ menuDTO.menu_name }  새 게시글 추가</h2>
	<form action="/Board/Write" method="POST">
	  <input type="hidden" name="menu_id" value="${ menuDTO.menu_id }" />
	  <table id = "table">
	    <tr>
	      <td>제목</td>
	      <td><input type="text" name="title"  /></td>
	    </tr>
	    <tr>
	      <td>작성자 이름</td>
	      <td><input type="text" name="writer" /></td>
	    </tr>
	    <tr>
	      <td>내용</td>
	      <td><textarea name="content" rows = "30" cols = "100"></textarea></td>
	    </tr>	 
	    <tr>	      
	      <td colspan="2">
	      <input type="submit" value="등록" />
	      <input type="button" value="목록" id = "goList"/>
	      </td>
	    </tr>
	  </table>
	</form>
  </main>	
    
  <script>

  	const goListEl = document.getElementById('goList');
  	goListEl.onclick = function(){
  		location.href = "/Board/List?menu_id=${ menuDTO.menu_id }";
  	}
  
    const  formEl = document.querySelector("form");
    const  titleEl = document.querySelector('[name="title"]');
    const  writerEl = document.querySelector('[name="writer"]');
   	formEl.addEventListener('submit', function(e){
   		if(titleEl.value == ""){
   			alert("제목을 입력하세여");
   			titleEl.focus();
   			e.preventDefault();
   			e.stopPropagation();
   			return false;
   		}
   		
   		if(writerEl.value == ""){
   			alert("작성자를 입력하세여");
   			writerEl.focus();
   			e.preventDefault();
   			e.stopPropagation();
   			return false;
   		}
   		
   		return true;
   	});
    
    

  </script>
  
  
  
</body>
</html>



