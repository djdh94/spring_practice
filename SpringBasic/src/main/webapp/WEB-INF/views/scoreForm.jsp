<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/score" method="post">
<input type="number" max="100" min="0" name="a" placeholder="수학"/><br/>
<input type="number" max="100" min="0" name="b" placeholder="영어"/><br/>
<input type="number" max="100" min="0" name="c" placeholder="언어"/><br/>
<input type="number" max="100" min="0" name="d" placeholder="사탐"/><br/>
<input type="number" max="100" min="0" name="ez" placeholder="컴퓨터"/><br/>
<input type="submit"/>
</form>


</body>
</html>