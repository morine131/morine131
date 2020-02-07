
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Chache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>管理者入力画面</title>
<jsp:include page="header.jsp"/>
<style>
/* 入力内容OK */
input[type=text]:valid {
	color: green;
}
input[type=email]:valid {
	color: green;
}
/* 入力内容NG */
input:invalid {
	border: dashed red;
	color: red;
}
</style>
</head>
<body>
<jsp:include page="nav.jsp" />

<div class="container">

<h3><font class="input-rule" >${message} </font></h3><br>

<br><br>

<form id="sender" action="${pageContext.request.contextPath}/Servlet/UserRegister" method="POST">
	<table class="table">

		<!-- ログインIDの表示（新規登録：編集可、更新：readonly） -->
		<tr>
			<c:choose>
				<c:when test="${dto.userid == null }">  <!-- 登録の場合 -->
					<th>ログインＩＤ<font class="input-rule">[必須　半角英数のみ（16文字まで）]</font></th>
					<td><input type="text" name="userid" required pattern="^[0-9A-Za-z]+$"
							value="<c:out value="${dto.userid }" />" maxlength="16" size="20" /> </td>
				</c:when>

				<c:when test="${dto.userid != null }">  <!-- 更新の場合 -->
					<th>ログインＩＤ<font class="input-rule">[変更できません]</font></th>
					<td><input disabled type="text" name="userid"
							value="<c:out value="${dto.userid}"/>" disabled /></td>
				</c:when>
			</c:choose>
		</tr>

		<!-- ニックネームの表示 -->
		<tr>
			<th>ニックネーム<font class="input-rule">[必須　16文字まで]</font></th>
			<td><input type="text" name="nickname" required
						value="<c:out value="${dto.nickname }" />" maxlength="16" size="40" /> </td>
		</tr>

		<!-- パスワードの表示 -->
		<tr>
			<th>パスワード<font class="input-rule">[必須　 半角英数のみ 16文字まで]</font></th>
			<td><input type="password" name="password" required pattern="^[0-9A-Za-z]+$"
						value="<c:out value="${dto.password }" />" maxlength="16" size="20" /></td>
		</tr>

		<!-- 利用者の場合のみ、メールアドレス、希望職種、希望地域を表示する -->
		<c:if test="${dto.roleid == 3 }">
			<tr>
				<th>メールアドレス<font class="input-rule">[必須]</font></th>
				<td><input type="email" name="mail" required
							value="<c:out value="${dto.mail }" />" maxlength="64" size="40" /></td>
			</tr>
			<tr>
				<th>希望職種</th>
				<td>
					<select name="typeid" id="typeid">
						<option value="1">プログラマ</option>
						<option value="2">システムエンジニア</option>
						<option value="3">ネットワークエンジニア</option>
						<option value="4">運用保守</option>
						<option value="5">サポート</option>
						<option value="6">その他</option>
					</select>

					<script type="text/javascript">
						document.getElementById('typeid').options[<c:out value="${dto.typeid - 1}" />].selected = true;
					</script>
				</td>
			</tr>
			<tr>
				<th>希望地域</th>
				<td><input type="text" name="area"
							value="<c:out value="${dto.area }" />" maxlength="4" size="20" /></td>
			</tr>
		</c:if>

		<!-- 職種ID（スーパー管理者や一般管理者の場合は typeid に"0"を送る） -->
		<c:if test="${loginUser.roleid < 3 }">
			<input type="hidden" name="typeid" value="0" />
		</c:if>

		<!-- 権限ID ---->
		<!-- （スーパー管理者の場合はログイン名が「admin」以外のステータスを変更できる（adminのステータス変更は不可 -->
		<!-- 一般管理者や利用者はステータスを変更できない -->
		<c:choose>
			<%-- 更新対象がadminの場合 --%>
			<c:when test="${dto.userid == 'admin' }">
				<tr>
					<th>ステータス<font class="input-rule">[変更できません]</font></th>
					<td>
						<select name="roleid" id="roleid">
							<option value="1">スーパー管理者</option>
						</select>
					</td>
				</tr>
			</c:when>

			<c:when test="${loginUser.roleid == 2 }"><%-- 一般管理者の場合 --%>
				<tr>
				<th>ステータス<font class="input-rule">[変更できません]</font></th>
					<td>
						<select name="roleid" id="roleid">
							<option value="2">一般管理者</option>
						</select>
					</td>
				</tr>
			</c:when>

			<c:when test="${dto.roleid == 3 }">	<%-- 利用者の場合 --%>
				<tr>
					<th>ステータス<font class="input-rule">[変更できません]</font></th>
					<td>
						<select name="roleid" id="roleid">
							<option value="3">利用者</option>
						</select>
					</td>
				</tr>
			</c:when>

			<c:otherwise><%-- スーパー管理者の場合かつ変更対象が「admin以外」の場合 --%>
				<tr>
					<th>ステータス<font class="input-rule">[必須]</font></th>
					<td>
						<select name="roleid" id="roleid">
							<option value="1">スーパー管理者</option>
							<option value="2">一般管理者</option>
						</select>
						<script type="text/javascript">
							document.getElementById('roleid').options[<c:out value="${dto.roleid - 1}" />].selected = true;
						</script>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>

	</table>

<!-- 利用者の新規作成はroleid="3"を送る -->
	<!-- admin/memberlistからのアクセスでない場合はloginから呼ばれているため、roleidはnullとなる -->
	<c:if test="${loginUser.roleid == null }">
		<input type="hidden" name="roleid" value="3" />
	</c:if>
</form>

	<!-- ログインIDがNULLの場合は、新規登録する -->
	<c:if test="${dto.userid == null}" >
		<input type="hidden" form="sender" name="userid" value="<c:out value="${dto.userid }" />" />
		<input type="hidden" form="sender" name="token" value="新規登録" >
		<input type="submit" form="sender" class="btn btn-success inline-btn" value="登録する" >
	</c:if>

	<!-- ログインIDに値がある場合は、更新する -->
	<c:if test="${dto.userid != null}" >
		<input type="hidden" form="sender"  name="userid" value="<c:out value="${dto.userid }" />" />
		<input type="hidden" form="sender" name="token" value="更新" />
		<input type="submit" form="sender" class="btn btn-success inline-btn" value="更新する" />
	</c:if>



<%-- 削除ボタン（表示ユーザ名が「admin」ではない、かつ、新規登録でない場合に表示 --%>
<c:if test="${dto.userid != 'admin' && token != '新規登録' }" >
	<form class="inline-btn" id="delete" action="${pageContext.request.contextPath}/Servlet/UserDelete" method="POST">
		<input type="hidden" name="userid" value="<c:out value="${dto.userid }" />" />
		<input type="hidden" name="roleid" value="<c:out value="${dto.roleid }" />" />
		<input type="hidden" name="token" value="削除" />
		<input type="submit" class="btn btn-warning inline-btn" value="削除する" />
	</form>
</c:if>
<br>
<br>
<%-- キャンセルボタン --%>
<button type="button" class="btn btn-danger" onClick="history.back()">キャンセル</button>


</div>
</body>
</html>