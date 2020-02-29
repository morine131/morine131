<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>求人票入力画面</title>
<jsp:include page="header.jsp" />

</head>
<body>
	<jsp:include page="nav.jsp" />
	<div class="container">
		<form id="sender1"
			<c:if test="${dto.jobid == 0 }" >action="${pageContext.request.contextPath}/Servlet/JobRegister" </c:if>
			<c:if test="${dto.jobid > 0}" >action="${pageContext.request.contextPath}/Servlet/JobUpdate" </c:if>
			method="GET">
			<input type="hidden" name="changejobid" id="update"
				value="<c:out value="${dto.jobid }" />" />
			<div class="jobinput-table">
				<table class="table jobinput-form">
					<tr>

							<th>求人票コード <font class="input-rule"><c:if test="${dto.jobid == 0 }" >[入力必須
									半角数字6桁以上9桁以内</c:if><c:if test="${dto.jobid > 0}">[求人票番号は更新できません]</c:if></font></th>

						<!-- 入力必須 0～9までのみ使用可能 6文字以上必須 -->
						<!-- ほんとはサーバー側で入力チェックしないと、セキュリティ的に弱い -->
					<td><input type="text" name="jobid" required <c:if test="${dto.jobid > 0}">readonly</c:if>
							pattern="^([0-9]{6,9})+$" value="<c:out value="${dto.jobid }" />"
							size="20" /></td>
					</tr>
					<tr>
						<th>会社名 <font class="input-rule">[入力必須 20文字以内]</font></th>
						<!--入力必須 'や"のクォーテーションを許可しない -->
						<td><input type="text" name="company" required maxlength='20'
							pattern="[^\x22\x27]*" value="<c:out value="${dto.company }" />"
							size="40" /></td>
					</tr>
					<tr>
						<th>郵便番号 <font class="input-rule">[入力必須 半角数字7桁 ハイフンなし]</font></th>
						<!-- 入力必須 0～9までのみ使用可能 7文字 -->
						<td><input type="text" name="postcode" required
							pattern="([0-9]{7})" value="<c:out value="${dto.postcode }" />"
							maxlength="7" size="10" /></td>
					<tr>
						<th>給与 <font class="input-rule">[入力必須 半角数字1桁以上3桁以内]</font></th>
						<!-- 入力必須 0～9までのみ使用可能 1文字以上必須 -->
						<td><input type="text" name="salary1" required maxlength='3'
							pattern="^([0-9]{1,})+$"
							value="<c:out value="${dto.salary1 }" />" size="10" />万円 ～ <input
							type="text" name="salary2" maxlength='3' pattern="^([0-9]{1,})+$"
							value="<c:out value="${dto.salary2 }" />" size="10" />万円</td>
					</tr>
					<tr>
						<th>雇用形態 <font class="input-rule">[選択必須]</font></th>
						<td><select name="empid" id="status1">
								<option value="1">正社員</option>
								<option value="2">契約社員</option>
								<option value="3">派遣社員</option>
								<option value="4">請負</option>
						</select>

						<!-- 			HTMLのselect属性にはdtoの値が反映されない→javascriptならselectのデフォルト値を変更できる　12/30外池 -->
						<script type="text/javascript">
							document.getElementById('status1').options[<c:out value="${dto.empid - 1}" />].selected = true;
						</script></td>
					</tr>
					<tr>
						<th>職種 <font class="input-rule">[選択必須]</font></th>
						<td><select name="typeid" id="status2">
								<option value="1">プログラマ</option>
								<option value="2">システムエンジニア</option>
								<option value="3">ネットワークエンジニア</option>
								<option value="4">運用保守</option>
								<option value="5">サポート</option>
								<option value="6">その他</option>
						</select>
						<script type="text/javascript">
							document.getElementById('status2').options[<c:out value="${dto.typeid - 1}" />].selected = true;
						</script></td>
					</tr>
					<tr>
						<th>公開設定 <font class="input-rule">[選択必須]</font></th>
						<td><input type="radio" name="publish" value="1" <c:if test="${dto.publish == 1 || dto.jobid == 0 }" > checked </c:if>>公開
							<input type="radio" name="publish" value="0"<c:if test="${dto.publish == 0 && dto.jobid != 0}" > checked </c:if>>非公開</td>
					</tr>
					<tr>
						<th>期限 <font class="input-rule"></font></th>
						<td><c:choose>
								<c:when test="${dto.inputvalidperiod != null }">
									<input type="date" name="inputvalidperiod"
										value="<c:out value="${dto.validperiod }"/>" size="10" />
								</c:when>
								<c:otherwise>
									<input type="date" name="inputvalidperiod"
										value="<c:out value="${dto.validperiod }"/>" size="10" />
								</c:otherwise>
							</c:choose></td>
					</tr>
					<tr>
						<th>勤務地 <font class="input-rule">[入力必須 4文字以内]</font></th>
						<!--入力必須  'や"のクォーテーションを許可しない -->
						<td><input type="text" name="workarea" required maxlength='4'
							pattern="[^\x22\x27]*" value="<c:out value="${dto.workarea }" />"
							size="7" /></td>
					</tr>

				</table>
			</div>

		</form>
		<input type="submit" class="btn btn-success table-btn inline-btn"
			id="sender1" form="sender1"
			<c:if test="${dto.jobid == 0 }" > value="登録する" </c:if>
			<c:if test="${dto.jobid > 0}" > value="更新する" </c:if> />

		<c:if test="${dto.jobid > 0}">
			<form id="delete"
				action="${pageContext.request.contextPath}/Servlet/JobDelete"
				method="GET" class="inline-btn">
				<input type="hidden" name="jobid"
					value="<c:out value="${dto.jobid }" />" /> <input type="submit"
					class="btn btn-warning  " value="削除する" />
			</form>
		</c:if>
		<br> <br>
		<form action="${pageContext.request.contextPath}/Servlet/JobSearch"
			method="GET">
			<input type="submit" class="btn btn-danger table-btn " value="キャンセル" />
		</form>
	</div>
</body>
</html>