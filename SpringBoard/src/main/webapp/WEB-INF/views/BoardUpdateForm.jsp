<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/boardUpdateForm" method="post">
<input type="text"  value="${board.writer }"readonly><br/>
<input type="text"  value="${board.title }"readonly>
<textarea rows="20" cols="100" readonly>${board.content }</textarea>
<input type="submit" value="수정하기">
</form>
</body>
</html>