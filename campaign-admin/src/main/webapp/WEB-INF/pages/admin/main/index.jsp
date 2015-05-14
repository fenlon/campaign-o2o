<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
<body ng-app="myApp">
	<div ng-controller="TextController">
		<div ng-view></div>
	</div>
</body>
</html>