<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${array }<hr/>
<%-- 위 array를
c:foreach를 이용해
하나하나 나열하기 --%>
<c:forEach var="array" items="${array }">
${array}<br/>
</c:forEach>
</body>
</html>