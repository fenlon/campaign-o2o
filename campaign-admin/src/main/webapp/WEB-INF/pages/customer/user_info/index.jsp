<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户中心-fenlonxiong@gmail.com</title>
<script type="text/javascript">
	var ctx = '${pageContext.request.contextPath	}';
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/common/angular/angular-route.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/userinfo.js"></script>
<style type="text/css">
/* Custom Styles */
.right-nav {
	border: 1px solid gray;
	border-radius: 18px;
}

.left-nav {
	background: #F3F3F3;
}

ul.nav-tabs {
	width: 200;
	margin-top: 20px;
	border-radius: 4px;
	border: 1px dashed #ddd;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
}

ul.nav-tabs li {
	margin: 0;
	border-top: 1px dashed #ddd;
}

ul.nav-tabs li:first-child {
	border-top: none;
}

ul.nav-tabs li a {
	margin: 0;
	padding: 8px 16px;
	border-radius: 0;
}

ul.nav-tabs li.active a,ul.nav-tabs li.active a:hover {
	color: #fff;
	background: #0088cc;
	border-bottom: 1px dashed #0088cc;
}

ul.nav-tabs li:first-child a {
	border-radius: 4px 4px 0 0;
}

ul.nav-tabs li:last-child a {
	border-radius: 0 0 4px 4px;
}

ul.nav-tabs.affix {
	top: 30px; /* Set the top position of pinned element */
}
</style>
</head>
<body data-spy="scroll" data-target="#myScrollspy" ng-app="userModule">
	<div class="container">
		<div class="row">
			<div class="col-xs-3" id="myScrollspy">
				<div class="left-nav text-center">
					<ul class="nav nav-tabs nav-stacked" data-spy="affix"
						data-offset-top="125">
						<li><a href="#base_info/${currentUser.encodeStr }">基本资料</a></li>
						<li><a href="#message">消息管理</a></li>
						<li><a href="#store">门店管理</a></li>
					</ul>
				</div>
			</div>
			<div class="col-xs-9">
				<div class="right-nav">
					<div ng-view></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
