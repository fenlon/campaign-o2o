<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%@ taglib uri="/WEB-INF/tags/mbaytags.tld" prefix="m"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<t:mobile-assets />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0 , maximum-scale=1.0, user-scalable=0">
<title>兑换界面</title>
<script type="text/javascript"
	src="${ctx }/js/o2o_campaign/operator/operator.js"></script>
<style>
* {
	margin: 0;
	padding: 0;
}

ul,ol,li {
	list-style: none;
}

html,body {
	width: 100%;
	height: 100%;
	font-family: 'Microsoft Yahei';
	font-size: 12px
}

table,caption,tbody,tfoot,thead,tr,th,td {
	font-size: 100%;
}

.con {
	height: 100%;
	width: 100%;
	background: #fff;
	background-size: cover;
	overflow: hidden
}

.body_con {
	width: 80%;
	margin: 10%;
	height: 90%
}

div.table table {
	border: 1px solid #C2C2C2;
	border-collapse: collapse;
	width: 99%;
}

div.table table td {
	text-align: center;
	padding: 15px 0px;
	background: #fff
}

div.table table th {
	font-size: 12px;
	color: #5D5C5C;
	padding: 5px 0px;
	background: #f3f3f3;
}
</style>
</head>
<body>
	<section class="con">
	<div class='body_con'>
		<div class='table' style='overflow: auto; height: 60%;'>
			<table style='overflow: auto'>
				<caption
					style='padding-bottom: 5px; font-size: 20px; font-weight: bold; text-align: center;'>兑换记录</caption>
				<thead>
					<tr>
						<th>操作员手机</th>
						<th>兑换码</th>
						<th>核销码</th>
						<th>日期</th>
					</tr>
				</thead>
				<tbody class="store-single">
					<c:forEach var="r" items="${records }">
						<tr>
							<td>${r.operator.cellphone }</td>
							<td>${r.redeemCode }</td>
							<td>${r.checkCode }</td>
							<td><joda:format value="${r.redeemTime }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</section>
</body>
</html>
