
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Chache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">

<title>確認画面</title>
<jsp:include page="header.jsp" />
</head>
<body>

<jsp:include page="nav.jsp" />

<div class="container">


<%-- 表示する確認画面 -------------------------------------------------------------------------------------%>

<c:choose>

	<%-- ログアウト処理の場合 --%>
	<c:when test="${commandMessage.equals('ログアウト')}">

		<h3 class="text-danger"> <c:out value="${commandMessage }" />します。よろしいでしょうか。</h3>

		<form action="${pageContext.request.contextPath}/Logout" method="POST">
			<table class="table">
				<tr>	<th>ログインＩＤ</th>	<td>${loginUser.userid}		</td>	</tr>
				<tr>	<th>ニックネーム</th>	<td>${loginUser.nickname}	</td>	</tr>
			</table>

			<input type="submit" name="submit" value="実行" class="btn btn-success"/>
			<button type="button" class="btn btn-success" onClick="history.back()">キャンセル</button>
		</form>
	</c:when>


	<%-- ユーザ新規登録／更新／削除処理の場合 --%>
	<c:otherwise>

		<h3 class="text-danger">以下の内容を<c:out value="${token }" />します。よろしいでしょうか。</h3>

		<form
			<c:if test="${token.equals('新規登録') || token.equals('更新') }" >
						action="${pageContext.request.contextPath}/Servlet/UserRegister?param=exec"	</c:if>
			<c:if test="${token.equals('削除')}" >
						action="${pageContext.request.contextPath}/Servlet/UserDelete?param=exec"	</c:if>
		method="POST">

			<table class="table">  <%-- リクエスト属性からユーザ情報を取得する --%>
				<tr> <td>ログインＩＤ	</td>	<td>${dto.userid}	</td>	</tr>

				<%-- ユーザ削除時は、ログインID、ステータスのみ表示 --%>

				<c:if test="${token != '削除' }">
					<tr> <td>ニックネーム	</td>	<td>${dto.nickname }</td>	</tr>
					<tr> <td>パスワード		</td>	<td>${dto.password }</td>	</tr>

					<%-- メールアドレス、希望職種、希望地域は、利用者のみ表示 --%>
					<c:if test="${dto.roleid == 3 }" >
						<tr> <td>メールアドレス	</td>	<td>${dto.mail }	</td>	</tr>
						<tr>
							<td>希望職種</td>
							<td>
								<c:choose>
									<c:when test="${dto.typeid == 0 }">Nullチェック用			</c:when>
									<c:when test="${dto.typeid == 1 }">プログラマ				</c:when>
									<c:when test="${dto.typeid == 2 }">システムエンジニア		</c:when>
									<c:when test="${dto.typeid == 3 }">ネットワークエンジニア	</c:when>
									<c:when test="${dto.typeid == 4 }">運用保守					</c:when>
									<c:when test="${dto.typeid == 5 }">サポート					</c:when>
									<c:when test="${dto.typeid == 6 }">その他					</c:when>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>希望地域</td>
							<td>${dto.area }</td>
						</tr>
					</c:if><%-- メールアドレス、希望職種、希望地域は、利用者のみ表示のEND --%>
				</c:if><%-- ユーザ削除時は、ログインID、ステータスのみ表示のEND --%>

				<tr>
					<td>ステータス</td>
					<td>
						<c:choose>
							<c:when test="${dto.roleid == 1 }">スーパー管理者	</c:when>
							<c:when test="${dto.roleid == 2 }">一般管理者		</c:when>
							<c:when test="${dto.roleid == 3 }">利用者			</c:when>
						</c:choose>
					</td>
				</tr>
			</table>

		<%-- <input type="hidden" name="token" value="${token}"> --%>

			<input type="submit" name="submit" value="実行" class="btn btn-success">
			<button type="button" class="btn  btn-warning" onClick="history.back()">修正</button>
			</form><br>
			<button type="button" class="btn btn-danger"
				onClick="location.href='${pageContext.request.contextPath}${returnPath}'">キャンセル</button>


	</c:otherwise>
</c:choose>

</div>

</body>
</html>