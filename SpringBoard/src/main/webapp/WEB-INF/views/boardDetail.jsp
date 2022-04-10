<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<h1 class="text text-info">${board.bno }번 글 상세페이지</h1>
		<div class="row">
			<div class="col-md-3">
				글제목 : <input type="text" class="form-control" value="${board.title }" readonly>
			</div>
			<div class="col-md-3">
				글쓴이 : <input type="text" class="form-control" value="${board.writer }" readonly>
			</div>
		</div>
			<textarea class="form-control" rows="10" readonly>${board.content }</textarea>
		<div class="row">
			<div class="col-md-3">쓴날짜 : </div>
			<div class="col-md-3">${board.regdate }</div>
			<div class="col-md-3">수정날짜 : </div>
			<div class="col-md-3">${board.updatedate }</div>
		</div>
	</div>
	<a href="/boardList"class="btn btn-success">글목록</a>
	<form action="/boardDelete" method="post">
	<input type="hidden" value="${board.bno }" name="bno"/>
	<input type="submit" value="삭제하기" class="btn btn-danger"/>
	</form>
	<form action="/boardUpdateForm" method="post">
	<input type="hidden" value="${board.bno }" name="bno"/>
	<input type="submit" value="수정"/>
	</form>
	
</body>
</html>