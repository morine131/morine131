
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="header.jsp" />

<title>トップページ</title>
</head>
<body>
	<jsp:include page="nav.jsp" />
	<c:out value="${pageContext.request.remoteUser}" />
	<script type="text/javascript">
		function clearText() {
			document.searchForm.company.value = "";
			document.searchForm.pref.value = "";
			document.searchForm.city.value = "";
			document.searchForm.city.value = "";
			document.searchForm.empstatus.value = "1";
			document.searchForm.typestatus.value = "1";
			document.searchForm.empstatus.value = "1";
			document.searchForm.workarea.value = "1";
			document.searchForm.salary1.value = "0";
			document.searchForm.salary2.value = "0";
		}
	</script>


	<div class="container">

		<h4>
			<font color="red">${message} </font>
			<%
				session.removeAttribute("message");
			%>
		</h4>
		<div class="jobsearch-table">

		<form action="${pageContext.request.contextPath}/Servlet/JobSearch" name="searchForm"
			method="post" id="search">
			<table class="table search-form table-bordered">
				<tr
					<c:if test="${loginUser.roleid != 1 && loginUser.roleid != 2 && loginUser.roleid != 3 }">class="blur"</c:if>>
					<td><p>会社名:</p> <input type="search" name="company"
						form="search"
						<c:if test="${loginUser.roleid != 1 && loginUser.roleid != 2 && loginUser.roleid != 3 }"> name="dammy1" disabled</c:if>
						value="<c:out value="${companyname }" />" size="20" />を含む</td>
					<td colspan="2"><p>所在県:</p> <input type="search" name="pref"
						form="search"
						<c:if test="${loginUser.roleid != 1 && loginUser.roleid != 2 && loginUser.roleid != 3 }">name="dammy2f" disabled</c:if>
						value="<c:out value="${pref }" />" size="20" />を含む</td>
					<td><p>所在市区:</p> <input type="search" name="city"
						form="search"
						<c:if test="${loginUser.roleid != 1 && loginUser.roleid != 2 && loginUser.roleid != 3 }">name="dammy3"disabled</c:if>
						value="<c:out value="${city }" />" size="20" />を含む</td>
				</tr>


				<tr>
					<td><p>雇用形態:</p> <select name="empstatus" id="empstatus"
						form="search">
							<option value="1">選択しない</option>
							<option value="2">正社員</option>
							<option value="3">契約社員</option>
							<option value="4">派遣社員</option>
							<option value="5">請負</option>
					</select> <script type="text/javascript">
						document.getElementById('empstatus').options[<c:out value="${empstatusNumber -1}" />].selected = true;
					</script></td>
					<td colspan="2"><p>職種:</p> <select name="typestatus"
						id="typestatus" form="search">
							<option value="1">選択しない</option>
							<option value="2">プログラマ</option>
							<option value="3">システムエンジニア</option>
							<option value="4">ネットワークエンジニア</option>
							<option value="5">運用保守</option>
							<option value="6">サポート</option>
							<option value="7">その他</option>
					</select> <script type="text/javascript">
						document.getElementById('typestatus').options[<c:out value="${typestatusNumber -1}" />].selected = true;
					</script></td>
					<td><p>就業場所:</p> <select name="workarea" id="workarea"
						form="search">
							<option value="1">選択しない</option>
							<option value="2">千葉</option>
							<option value="3">東京</option>
					</select>
					<script type="text/javascript">
						document.getElementById('workarea').options[<c:out value="${workareaNumber -1}" />].selected = true;
					</script></td>
				<tr
					<c:if test="${loginUser.roleid != 1 && loginUser.roleid != 2 && loginUser.roleid != 3 }">class="blur"</c:if>>
					<td colspan="2"><p>最低月給:</p> <input type="number"
						name="salary1" required step="1" min="0" form="search"
						<c:if test="${loginUser.roleid != 1 && loginUser.roleid != 2 && loginUser.roleid != 3 }" >disabled name="dammy4"</c:if>
						<c:if test="${salary1 != null}"> value="<c:out value="${salary1 }" />"	</c:if>
						<c:if test="${salary1 == null}"> value="0" </c:if> />万円以上</td>
					<td colspan="2"><p>最高月給:</p> <input type="number"
						name="salary2" form="search" required step="1" min="0"
						<c:if test="${loginUser.roleid != 1 && loginUser.roleid != 2 && loginUser.roleid != 3 }">disabled name="dammy5"</c:if>
						<c:if test="${salary2 != null}"> value="<c:out value="${salary2 }" />"	</c:if>
						<c:if test="${salary2 == null}"> value="0" </c:if> />万円以上</td>
				</tr>
			</table>
			<c:if
				test="${loginUser.roleid != 1 && loginUser.roleid != 2 && loginUser.roleid != 3 }">　※ログイン後、全ての検索機能が利用できます。</c:if>

			<c:if
				test="${loginUser.roleid != 1 && loginUser.roleid != 2 && loginUser.roleid != 3 }">
				<input type="hidden" name="salary1" value="0" />
				<input type="hidden" name="salary2" value="0" />
				<input type="hidden" name="company" value="" />
				<input type="hidden" name="pref" value="" />
				<input type="hidden" name="city" value="" />
			</c:if>
			<input type="submit" class="btn btn-primary table-btn" value="検索する" />
			<input type="button"class="btn btn-primary " onClick="clearText( )" value="リセット">
		</form>

		<br> <br>
		<form id="sender"
			action="${pageContext.request.contextPath}/Servlet/JobSearch"
			method="GET" class="inline-btn">
			<input type="submit" class="btn btn-primary inline-btn " value="全件表示" />
		</form>
		<c:if test="${loginUser.roleid == 2 ||loginUser.roleid == 1 }">
			<form action="${pageContext.request.contextPath}/Servlet/JobInput"
				method="POST" class="inline-btn">
				<input type="submit" class="btn btn-primary " value="求人票登録" />
			</form>
		</c:if>

	</div>
		<table class="table table-striped table-bordered" style="table-layout:fixed;">
			<tr>
				<th style="width:100px;">求人票コード</th>
				<th style="width:180px;">会社名</th>
				<th style="width:60px;">所在県</th>
				<th class="hirogetai" >所在市区</th>
				<th>給与（月給）</th>
				<th style="width:75px;"  >雇用形態</th>
				<th>職種</th>
				<th style="width:75px;" >就業場所</th>
				<th>更新日時</th>
				<th>リンク</th>
			</tr>

			<c:forEach items="${jobList}" var="dto">
				<c:if
					test="${dto.pdfname != null || loginUser.roleid == 2 ||loginUser.roleid == 1 }">
					<tr>
						<td><c:out value="${dto.jobid }"></c:out></td>
						<td><c:out value="${dto.company }"></c:out></td>
						<td><c:out value="${dto.pref }"></c:out></td>
						<td><c:out value="${dto.city }"></c:out></td>
						<td><c:out value="${dto.salary1 }" />万 〜 <c:out
								value="${dto.salary2 }" />万円</td>
						<td><c:out value="${dto.empstatus}"></c:out></td>
						<td><c:out value="${dto.typestatus }"></c:out></td>
						<td><c:out value="${dto.workarea }"></c:out></td>
						<td><c:out value="${dto.joblastupdate }"></c:out></td>

						<td>

							<div>
								<form action="${pageContext.request.contextPath}/Servlet/PDF"
									target="_blank" method="GET" class="inline-btn">
									<input type="hidden" name="pdfname"
										value="<c:out value="${dto.pdfname }" />" />
									<c:if test="${dto.pdfname != null }">
										<input type="submit" class="btn btn-success" id="pdf"
											value="詳細PDF" />
									</c:if>

								</form>

								<form
									action="${pageContext.request.contextPath}/Servlet/UploadDisplay"
									method="GET" class="inline-btn">
									<input type="hidden" name="jobid" id="pdf"
										value="<c:out value="${dto.jobid }" />" />
									<c:if test="${loginUser.roleid == 2 ||loginUser.roleid == 1 }">
										<input type="submit" class="btn btn-success" id="pdf"
											value="PDF登録更新" />
									</c:if>
								</form>
							</div>
							<div>
								<form
									action="${pageContext.request.contextPath}/Servlet/JobDetail"
									method="GET" class="inline-btn">
									<input type="hidden" name="jobid" id="update"
										value="<c:out value="${dto.jobid }" />" />
									<c:if test="${loginUser.roleid == 2 ||loginUser.roleid == 1 }">
										<input type="submit" class="btn btn-success" id="update"
											value="更新削除" />
									</c:if>
								</form>
								<form
									action="${pageContext.request.contextPath}/Servlet/FeedBackSearch"
									method="GET" class="inline-btn">
									<input type="hidden" name="jobid" id="fbid"
										value="<c:out value="${dto.jobid }" />" /> <input
										type="hidden" name="company" id="company"
										value="<c:out value="${dto.company }" />" />
									<c:if
										test="${loginUser.roleid == 3 || loginUser.roleid == 2 || loginUser.roleid == 1 }">
										<input type="submit" class="btn btn-success" id="fbid"
											value="就活体験談" />
									</c:if>
								</form>
							</div>
						</td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
</body>
</html>