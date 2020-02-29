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
<title>就活体験談一覧</title>
</head>
<body>
<jsp:include page="nav.jsp" />
	<div class="container">
			<h4>
			<font color="red">${feedbackmessage} </font>
			 <% session.removeAttribute("feedbackmessage");%>
		</h4>

	<table class="table table-striped  table-bordered" >
	<h4>会社名：<c:out value="${company }"></c:out></h4>

		<tr>
			<th class="taikendan">就活体験談内容</th>
			<th class="taikendan">ニックネーム</th>
			<th class="taikendan">更新日時</th>
			<th class="taikendan">ボタン表示欄</th>
		</tr>
		<c:forEach items="${feedbackList}" var="fdto">
			<tr>
				<td class="taikennaiyou"><c:out value="${fdto.fbcontent }"></c:out></td>
				<td><c:if test="${fdto.nickname != null }"><c:out value="${fdto.nickname }" /></c:if> <c:if test="${fdto.nickname == null }">※すでに存在しないユーザーの書き込みです</c:if></td>
				<td><c:out value="${fdto.fblastupdate }"></c:out></td>
				<td><form  action="${pageContext.request.contextPath}/Servlet/FeedBackDetail" method="GET">
				<c:if test="${loginUser.roleid == 2 ||loginUser.roleid == 1 || loginUser.nickname == fdto.nickname }">
						<input type="hidden" name="fbid" value="<c:out value="${fdto.fbid }" />" />
						<input type="hidden" name="userid" value="<c:out value="${fdto.userid }" />" />
						<input type="submit" class="btn btn-success" value="更新削除" />
		</c:if>

					</form></td>

<%-- 				<td><a href="detail?id=<c:out value="${dto.userid }"/>">詳細画面へ</a></td> --%>

			</tr>
		</c:forEach>
	</table>
<br>
		<form id="sender" action="${pageContext.request.contextPath}/Servlet/FeedBackInput " >
			<input type="hidden" name="fbid" value="<c:out value="${fbdto.fbid}" />" />
			<input type="hidden" name="token" value="<c:out value="${token}" />" />
			<input type="hidden" name="jobid" value="<c:out value="${jobid}" />" />
			<input type="hidden" name="userid" value="<c:out value="${loginUser.userid}" />" />
			<input type="submit" class="btn btn-success" value="就活体験談を投稿する" />
			<br>
	</form>
		<br>
	<form  action="${pageContext.request.contextPath}/Servlet/JobSearch" method="GET">
				<input type="submit" class="btn btn-danger" value="求人一覧に戻る" />
			</form>
	</div>
</body>
</html>