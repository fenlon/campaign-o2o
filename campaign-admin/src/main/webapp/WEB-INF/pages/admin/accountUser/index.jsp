<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="accountUser">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>管理首页</title>

<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
	var extjsPath = "http://222.22.91.35:8080/ext-4.2.1.883/";
</script>

<%-- <link
	href="${pageContext.request.contextPath }/resources/style/common/bootstrap3/bootstrap.min.css"
	rel="stylesheet" type="text/css" /> --%>

<link
	href="${pageContext.request.contextPath }/resources/style/common/angular-ui/ui-grid-unstable.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/angularjs/angular.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/angularjs/angular-touch.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/angularjs/angular-animate.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/angularjs/angular-route.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/angularjs/angular-resource.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/js/angular-ui/csv.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/js/angular-ui/pdfmake.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/js/angular-ui/vfs_fonts.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/js/angular-ui/ui-grid-unstable.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/admin/accountUser/index.js"></script>
<style type="text/css">
.grid {
	width: 100%;
	height: 800px;
}
</style>
</head>
<body>
	<div ng-controller="AccountUserCtrl">
		<div ui-grid="gridOptions" class="grid" ui-grid-resize-columns
			ui-grid-move-columns ui-grid-pagination></div>
	</div>
</body>
</html>