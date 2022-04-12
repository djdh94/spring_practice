<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="container">
		<table class="table table-hover">
		<thead>
		<tr>
		<td>글번호</td>
		<td>글제목</td>
		<td>글내용</td>
		<td>글쓴이</td>
		<td>등록날짜</td>
		<td>수정날짜</td>
		</tr> 
		</thead>
		<tbody>
		<c:forEach var="List" items="${List }">
		<tr> 
		<td>${List.bno }</td>
		<td><a href="/boardDetail/${List.bno}">${List.title }</a></td>
		<td>${List.content }</td>
		<td>${List.writer }</td>
		<td>${List.regdate }</td>
		<td>${List.updatedate }</td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
		<a href="/boardInsert"class="btn btn-success">글쓰기</a>
		${pageMaker}
		</div>
		<nav aria-label="Page navigation example">
		<!--  이전페이지 버튼 보일지 말지 결정하는 부분 -->
		  <ul class="pagination justify-content-center">
		<c:if test="${pageMaker.prev }">
	    	<li class="page-item">
		      <a class="page-link" href="/boardList?pageNum=${pageMaker.startPage -1}">
		      	&laquo;
		      </a>
	    	</li>
    	</c:if>
    	<!-- 밑에 깔아줄 버튼들 -->
    	<c:forEach var="idx" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
		    <li class="page-item ${pageMaker.cri.pageNum eq idx ? 'active' : ''}">
		    	<a class="page-link" href="/boardList?pageNum=${idx}">
		    		${idx }
		    	</a>
		    </li>
		</c:forEach>
	    <c:if test="${pageMaker.next }">
		    <li class="page-item">
		      <a class="page-link" href="/boardList?pageNum=${pageMaker.endPage +1}">
		        &raquo;
		      </a>
		    </li>
	    </c:if>
  </ul>
	</nav>
</body>
</html>