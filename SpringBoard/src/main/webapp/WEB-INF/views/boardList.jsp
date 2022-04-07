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
</body>
</html>