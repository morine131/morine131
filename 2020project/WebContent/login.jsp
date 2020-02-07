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
<title>ログイン画面</title>
</head>
<body>

<div align="center">

<h2>求人票検索システム</h2>

<h3 class="text-danger">${message}</h3><br>

<%-- ログインフォーム表示 --%>
<form method="POST" action="${pageContext.request.contextPath}/Login">
	<table>
		<tr>
			<td>ログインID</td>
			<td><input type="text" name="userid"></td>
		</tr>
		<tr>
			<td>パスワード</td>
			<td><input type="password" name="password"></td>
		</tr>
	</table>
	<br>
	<input type="submit" value="ログイン" name="submit"><br><br>
</form>
</div>


<%-- キャンセルボタン --%>
<div align="center">
<form method="get" action="${pageContext.request.contextPath}/index.jsp">
	<input type="submit" value="キャンセル"><br><br>
</form>


<%-- 新規登録ボタン --%>
<form method="POST" action="${pageContext.request.contextPath}/Servlet/UserInput">
	<input type="hidden" name="returnPath" value="/index.jsp" >
	<input type="hidden" name="roleid" value="3" >
	<input type="hidden" name="token" value="新規登録" >
	<input type="submit" value="新規登録はこちら" >
</form>
<%-- パラメータを隠すためform～POSTに変更<a href="${pageContext.request.contextPath}/Servlet/UserInput?roleid=3">新規登録はこちら</a> --%>


</div>

</body>
</html>