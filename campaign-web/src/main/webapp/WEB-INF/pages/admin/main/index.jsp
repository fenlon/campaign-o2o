<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>管理首页</title>
<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular-route.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular-route.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular-resource.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/admin/main/index.js"></script>
</head>
<div class="container">
	<!-- Main component for a primary marketing message or call to action -->
	<div class="jumbotron">
		<h1>O2O 红包平台</h1>
		<p>This example is a quick exercise to illustrate how the default,
			static and fixed to top navbar work. It includes the responsive CSS
			and HTML, so it also adapts to your viewport and device.</p>
		<p>To see the difference between static and fixed top navbars,
			just scroll.</p>
		<p>
			<c:choose>
				<c:when test="${ empty currentUser }">
					<a class="btn btn-lg btn-primary"
						href="${pageContext.request.contextPath }/login/index.htm"
						role="button">登录 &raquo;</a>
					<a class="btn btn-lg btn-primary"
						href="${pageContext.request.contextPath }/register/index.htm"
						role="button">注册 &raquo;</a>
				</c:when>
				<c:otherwise>
					<a class="btn btn-lg btn-primary"
						href="${pageContext.request.contextPath }/store_campaign/create.htm"
						role="button">发布活动&raquo;</a>
				</c:otherwise>
			</c:choose>
		</p>
	</div>
</div>
</html>