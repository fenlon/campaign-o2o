<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon"
	href="${pageContext.request.contextPath }/resources/images/logo/logo_title.png" />
<title><decorator:title default="Welcome!" />-Fenlon Xiong</title>
<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
</script>
<decorator:head />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap-theme.min.css">
<style type="text/css">
body {
	min-height: 2000px;
	padding-top: 70px;
}
</style>
</head>
<body
	<decorator:getProperty property="body.ng-app" writeEntireProperty="true"/>>
	<div id="header">
		<%@ include file="/WEB-INF/pages/commons/header.jsp"%>
	</div>
	<div id="mainbody">
		<decorator:body />
	</div>
	<div id="footer">
		<%@ include file="/WEB-INF/pages/commons/footer.jsp"%>
	</div>
	<script
		src="${pageContext.request.contextPath }/resources/common/jquery/jquery-2.1.3.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap.min.js"></script>
</body>
</html>
