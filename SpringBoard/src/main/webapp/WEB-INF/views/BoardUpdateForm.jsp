<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/boardUpdate" method="post">
<input type="hidden" name="bno" value="${board.bno }"><br/>
<input type="text"  name="writer" value="${board.writer }"><br/>
<input type="text" name="title" value="${board.title }">
<textarea rows="20" name="content" cols="100" >${board.content }</textarea>
<input type="submit" value="수정하기">
</form>
</body>
</html>