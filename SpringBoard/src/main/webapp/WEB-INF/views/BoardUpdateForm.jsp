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
	<input type="hidden" name="bno" value="${board.bno }"/>
	<input type="text"  name="writer" value="${board.writer }">
	<input type="text" name="title" value="${board.title }"><br/>
	<textarea rows="20" name="content" cols="100" >${board.content }</textarea><br/>

	<!-- 수정을 했다면, 완료후에도 페이지번호,검색조건,검색어가 유지되도록 전달받은 데이터를 폼으로 다시 넘겨줘야함 
	 넘겨받은 searchType,keyword,pageNum을 재전달하도록 폼내부에 추가하기-->
	 <input type="hidden" name="pageNum" value="${param.pageNum }">
	<input type="hidden" name="searchType" value="${param.searchType }">
	<input type="hidden" name="keyword" value="${param.keyword }">
	<input type="submit" value="수정하기">
	<input type="reset" value="초기화"/>
</form>
</body>
</html>