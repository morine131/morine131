<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="header.jsp" />
<title>利用者の一覧</title>
</head>
<body>

<jsp:include page="nav.jsp" />

<div class="container">
<h4>利用者一覧</h4>

<h3><font color="red" >${message} </font></h3><br>

<%-- 新規登録ボタン --%>
<form action="${pageContext.request.contextPath}/Servlet/UserInput" method="POST" >
	<input type="hidden" name="roleid" value="3" >
	<input type="hidden" name="token" value="新規登録" >
	<input type="hidden" name="returnPath" value="/Servlet/MemberSearch">
	<input type="submit" class="btn btn-success" value="新規登録" >
</form>
<br>

<%-- 利用者一覧を表示 --%>
<table class="table table-bordered">
	<tr>
		<th>ログインＩＤ</th>
		<th>ニックネーム</th>
		<th>ステータス</th>
		<th>リンク</th>
	</tr>

	<c:forEach items="${memberList}" var="dto">
		<tr>
			<td><c:out value="${dto.userid }">		</c:out></td>
			<td><c:out value="${dto.nickname }">	</c:out></td>
			<td><c:out value="${dto.rolename}">		</c:out></td>

			<td><%-- 更新/削除ボタン --%>
				<form  action="${pageContext.request.contextPath}/Servlet/UserDetail" method="POST">
					<input type="hidden" name="userid" value="<c:out value="${dto.userid }" />" />
					<input type="hidden" name="returnPath" value="/Servlet/MemberSearch">
					<input type="submit" class="btn btn-success" value="更新/削除" />
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
</div>
</body>
</html>