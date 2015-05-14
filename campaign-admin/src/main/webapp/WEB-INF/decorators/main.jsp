<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<link rel="icon"
	href="${pageContext.request.contextPath }/resources/images/logo/logo_title.png" />
<title><decorator:title default="Welcome!" />-Fenlon Xiong</title>
<t:assets />
<decorator:head />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap-theme.min.css">
</head>
<body
	<decorator:getProperty property="body.ng-app" writeEntireProperty="true"/>>
	<div id="header" class="container">
		<%@ include file="/WEB-INF/pages/commons/header.jsp"%>
	</div>

	<div id="mainbody" class="container">
		<decorator:body />
	</div>
	<div id="footer" class="container">
		<%@ include file="/WEB-INF/pages/commons/footer.jsp"%>
	</div>

	<!-- /.container -->

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="${pageContext.request.contextPath }/resources/common/jquery/jquery-2.1.3.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="${pageContext.request.contextPath }/resources/common/bootstarp3/bootstrap.min.js"></script>
</body>
</html>
